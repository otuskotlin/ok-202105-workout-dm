package service

import Mapper.setQuery
import Mapper.toCreateResponse
import Mapper.toDeleteResponse
import Mapper.toReadResponse
import Mapper.toSearchResponse
import Mapper.toUpdateResponse
import ModelForRequest.cruds.*
import StubData
import WorkoutCrud
import context.MpContext

class WorkoutService(
	var crud: WorkoutCrud
) {

	suspend fun createWorkout(context: MpContext, request: CreateWorkoutRequest): CreateWorkoutResponse {
		context.setQuery(request)
		crud.create(context)
		return context.toCreateResponse()
	}

	suspend fun readWorkout(context: MpContext, request: ReadWorkoutRequest): ReadWorkoutResponse {
		context.responseWorkout = StubData.getModel()
		crud.read(context.setQuery(request))
		return context.toReadResponse()
	}

	suspend fun updateWorkout(context: MpContext, request: UpdateWorkoutRequest): UpdateWorkoutResponse {
		context.responseWorkout = StubData.getModel()

		crud.update(context.setQuery(request))
		return context.toUpdateResponse()
	}

	suspend fun deleteWorkout(context: MpContext, request: DeleteWorkoutRequest): DeleteWorkoutResponse {
		context.responseWorkout = StubData.getModel()
		crud.delete(context.setQuery(request))
		return context.toDeleteResponse()
	}

	suspend fun searchWorkout(context: MpContext, request: SearchWorkoutRequest): SearchWorkoutResponse {
		context.responseWorkouts = StubData.getModels().toMutableList()
		crud.search(context.setQuery(request))
		return context.toSearchResponse()
	}

}