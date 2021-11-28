import ModelForRequest.CreateWorkout
import kotlinx.coroutines.runBlocking
import repo.DbWorkoutFilterRequest
import repo.DbWorkoutIdRequest
import repo.DbWorkoutModelRequest
import repo.DbWorkoutResponse
import repo.IRepoWorkout

class RepoWorkoutSql(
	private val initObjects: List<CreateWorkout>

): IRepoWorkout {
	init {
		runBlocking {
			initObjects.forEach{ save(it)}
		}
	}
	suspend fun save(req: CreateWorkout): DbWorkoutResponse {
		TODO("Not yet implemented")
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
}