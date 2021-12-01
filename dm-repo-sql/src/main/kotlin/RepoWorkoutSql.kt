import kotlinx.coroutines.runBlocking
import model.OwnerIdModel
import model.WorkoutIdModel
import model.WorkoutModel
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repository.DbWorkoutFilterRequest
import repository.DbWorkoutIdRequest
import repository.DbWorkoutModelRequest
import repository.DbWorkoutResponse
import repository.IRepoWorkout
import table.ExercisesTable
import table.UsersTable
import table.WorkoutTable
import java.sql.SQLException
import java.util.*

class RepoWorkoutSql(
	url: String = "jdbc:postgresql://localhost:5432/sport_project",
	user: String = "postgres",
	password: String = "marketplace-pass",
	schema: String = "marketplace",

	private val initObjects: List<WorkoutModel>

) : IRepoWorkout {

	private val db by lazy { SqlConnector(url, user, password, schema).connection(WorkoutTable, ExercisesTable) }

	init {
		runBlocking {
			initObjects.forEach {
				save(it)
			}
		}
	}

	private suspend fun save(item: WorkoutModel): DbWorkoutResponse {
		return safeTransaction({
			val realOwnerId = UsersTable.insert {
				if (item.ownerId != OwnerIdModel.NONE) {
					it[id] = item.ownerId.asUUID()
				}
//				it[name] = item.ownerId.asUUID().toString()
			} get UsersTable.id

			val exersieceId = mutableListOf<UUID>()
			item.items.forEach { ex ->
				exersieceId.add(ExercisesTable.insert {
					it[nameExercise] = ex.nameExercise
					it[numberRepetitions] = ex.numberRepetitions
					it[weight] = ex.weight
					it[retry] = ex.retry
					it[ownWeight] = ex.ownWeight
				} get UsersTable.id)
			}

			val res = table.WorkoutTable.insert {
				if (item.id != WorkoutIdModel.NONE) {
					it[id] = item.id.asUUID()
				}
				it[description] = item.description
				it[ownerId] = realOwnerId
				it[name] = item.name
				it[exercises] = exersieceId

			}

			DbWorkoutResponse(WorkoutTable.from(res), true)
		}, {
			DbWorkoutResponse(
				result = null,
				isSuccess = false,
//				errors = listOf(ModelForRequest.ApiError())
			)
		})
	}

	override suspend fun create(req: DbWorkoutModelRequest): DbWorkoutResponse {
		TODO("Not yet implemented")
	}

	override suspend fun read(req: DbWorkoutIdRequest): DbWorkoutResponse {
		TODO("Not yet implemented")
	}

	override suspend fun update(req: DbWorkoutModelRequest): DbWorkoutResponse {
		TODO("Not yet implemented")
	}

	override suspend fun delete(req: DbWorkoutIdRequest): DbWorkoutResponse {
		TODO("Not yet implemented")
	}

	override suspend fun search(req: DbWorkoutFilterRequest): DbWorkoutResponse {
		TODO("Not yet implemented")
	}

	private fun <T> safeTransaction(statement: Transaction.() -> T, handleException: Throwable.() -> T): T {
		return try {
			transaction(db, statement)
		} catch (e: SQLException) {
			throw e
		} catch (e: Throwable) {
			return handleException(e)
		}
	}
}