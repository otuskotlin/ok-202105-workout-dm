import kotlinx.coroutines.runBlocking
import model.CommonErrorModel
import model.OwnerIdModel
import model.WorkoutIdModel
import model.WorkoutModel
import org.jetbrains.exposed.sql.*
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

    private val initObjects: List<WorkoutModel> = mutableListOf()

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

    private suspend fun save(item: WorkoutModel): DbWorkoutResponse {
        return safeTransaction({
            println("Start save")
            val realOwnerId = UsersTable.insert {
                if (item.ownerId != OwnerIdModel.NONE) {
                    it[id] = item.ownerId.asUUID()
                }
//				it[name] = item
//				it[surname] = item.
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
        return safeTransaction({
            val result =
                (WorkoutTable innerJoin UsersTable).select { WorkoutTable.id.eq(req.id.asUUID()) }
                    .single()
            val workoutModel: WorkoutModel = WorkoutTable.from(result)
            ExercisesTable.select { workout_id.eq(req.id.asUUID()) }.forEach {
                workoutModel.excersices.add(ExercisesTable.from(it))
            }

            DbWorkoutResponse(workoutModel, true)
        }, {
            val err = when (this) {
                is NoSuchElementException -> CommonErrorModel(field = "id", message = "Not Found")
                is IllegalArgumentException -> CommonErrorModel(message = "More than one element with the same id")
                else -> CommonErrorModel(message = localizedMessage)
            }
            DbWorkoutResponse(result = null, isSuccess = false)
        })
    }

    override suspend fun update(model: DbWorkoutModelRequest): DbWorkoutResponse {
        val workoutModel = model.workoutModel
        return safeTransaction({
            println("Start save")
            val realOwnerId = UsersTable.update {
                if (workoutModel.ownerId != OwnerIdModel.NONE) {
                    it[id] = workoutModel.ownerId.asUUID()
                }
            }

            WorkoutTable.update({ UsersTable.id.eq(workoutModel.id.asUUID()) }) {
                if (workoutModel.id != WorkoutIdModel.NONE) {
                    it[id] = workoutModel.id.asUUID()
                }
                it[description] = workoutModel.description
                it[ownerId] = workoutModel.ownerId.asUUID()
                it[name] = workoutModel.name
            }

            ExercisesTable.deleteWhere {
                workout_id.eq(workoutModel.id.asUUID())
            }

            ExercisesTable.batchInsert(workoutModel.excersices) { exerciseModel ->
                this[nameExercise] = exerciseModel.nameExercise
                this[retry] = exerciseModel.retry
                this[weight] = exerciseModel.weight
                this[ownWeight] = exerciseModel.ownWeight
                this[numberRepetitions] = exerciseModel.numberRepetitions
                this[workout_id] = workoutModel.id.asUUID()
            }
            val result =
                WorkoutTable.select { WorkoutTable.id.eq(workoutModel.id.asUUID()) }.single()

            DbWorkoutResponse(WorkoutTable.from(result), true)
        }, {
            DbWorkoutResponse(
                result = null,
                isSuccess = false,
//				errors = listOf(ModelForRequest.ApiError())
            )
        })
    }

    override suspend fun delete(req: DbWorkoutIdRequest): DbWorkoutResponse {
        return safeTransaction({
            val result = WorkoutTable.select { WorkoutTable.id.eq(req.id.asUUID()) }.single()
            WorkoutTable.deleteWhere { WorkoutTable.id eq req.id.asUUID()  }
//            ExercisesTable.deleteWhere { workout_id eq req.id.asUUID() }

            DbWorkoutResponse(result = WorkoutTable.from(result), isSuccess = true)
        }, {
            DbWorkoutResponse(
                result = null,
                isSuccess = false,
//				errors = listOf(CommonErrorModel(field = "id", message = "Not Found"))
            )
        })
    }

    override suspend fun search(req: DbWorkoutFilterRequest): DbWorkoutsResponse {
        return safeTransaction({
            val workoutModelList: MutableList<WorkoutModel> = (WorkoutTable innerJoin UsersTable)
                .select { WorkoutTable.id.eq(req.ownerId.asUUID()) }
                .map { WorkoutTable.from(it) }
                .toMutableList()

            workoutModelList.forEach {
                it.excersices = ExercisesTable.select() { workout_id.eq(it.id.asUUID()) }
                    .map { ExercisesTable.from(it) }
                    .toMutableList()
            }

            DbWorkoutsResponse(workoutModelList, true)
        }, {
//			val err = when (this) {
//				is NoSuchElementException -> CommonErrorModel(field = "id", message = "Not Found")
//				is IllegalArgumentException -> CommonErrorModel(message = "More than one element with the same id")
//				else -> CommonErrorModel(message = localizedMessage)
//			}
            DbWorkoutsResponse(result = mutableListOf(), isSuccess = false)
        })
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