package repo

interface IRepoAd {
	suspend fun create(req: DbWorkoutModelRequest): DbWorkoutResponse
	suspend fun read(req: DbWorkoutIdRequest): DbWorkoutResponse
	suspend fun update(req: DbWorkoutModelRequest): DbWorkoutResponse
	suspend fun delete(req: DbWorkoutIdRequest): DbWorkoutResponse
	suspend fun search(req: DbWorkoutFilterRequest): DbWorkoutResponse

	object NONE : IRepoAd {
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
}
