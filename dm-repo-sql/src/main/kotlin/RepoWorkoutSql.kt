import kotlinx.coroutines.runBlocking
import model.OwnerIdModel
import model.WorkoutIdModel
import model.WorkoutModel
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import repository.*
import table.ExercisesTable
import table.ExercisesTable.nameExercise
import table.ExercisesTable.numberRepetitions
import table.ExercisesTable.ownWeight
import table.ExercisesTable.retry
import table.ExercisesTable.weight
import table.ExercisesTable.workout_id
import table.UsersTable
import table.WorkoutTable
import java.sql.SQLException

class RepoWorkoutSql(
    url: String = "jdbc:postgresql://localhost:5432/",
    user: String = "postgres",
    password: String = "postgres",
    schema: String = "sport_project",

    private val initObjects: List<WorkoutModel>

) : IRepoWorkout {

    private val db by lazy {
        SqlConnector(url, user, password, schema).connection(
            WorkoutTable,
            ExercisesTable
        )
    }

    init {
        runBlocking {
            initObjects.forEach {
                save(it)
            }
        }
    }

    public suspend fun save(item: WorkoutModel): DbWorkoutResponse {
        return safeTransaction({
        println("Start save")
            val realOwnerId = UsersTable.insert {
                if (item.ownerId != OwnerIdModel.NONE) {
                    it[id] = item.ownerId.asUUID()
                }
                it[name] = "Test"
                it[surname] = "sureTest"
            } get UsersTable.id


            val res: InsertStatement<Number> = WorkoutTable.insert {
                if (item.id != WorkoutIdModel.NONE) {
                    it[id] = item.id.asUUID()
                }
                it[description] = item.description
                it[ownerId] = realOwnerId
                it[name] = item.name
            }

            val result = WorkoutTable.from(res)

            ExercisesTable.batchInsert(item.excersices) { exerciseModel ->
                this[nameExercise] = exerciseModel.nameExercise
                this[retry] = exerciseModel.retry
                this[weight] = exerciseModel.weight
                this[ownWeight] = exerciseModel.ownWeight
                this[numberRepetitions] = exerciseModel.numberRepetitions
                this[workout_id] = result.id.asUUID()
            }

            DbWorkoutResponse(result, true)
        }, {
            DbWorkoutResponse(
                result = null,
                isSuccess = false,
//				errors = listOf(ModelForRequest.ApiError())
            )
        })
    }

    override suspend fun create(req: DbWorkoutModelRequest): DbWorkoutResponse {
        return save(req.workoutModel)
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

    private fun <T> safeTransaction(
        statement: Transaction.() -> T,
        handleException: Throwable.() -> T
    ): T {
        return try {
            transaction(db, statement)
        } catch (e: SQLException) {
            throw e
        } catch (e: Throwable) {
            return handleException(e)
        }
    }
}