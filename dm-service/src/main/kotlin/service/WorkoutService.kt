package service

import DataNotAllowedException
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

	suspend fun errorAd(context: MpContext, e: Throwable): BaseMessage {
		context.addError(e)
		return context.toReadResponse()
	}

	suspend fun handleWorkout(context: MpContext, request: BaseMessage): BaseMessage = try {
		when (request) {
			is CreateWorkoutRequest -> createWorkout(context, request)
			is ReadWorkoutRequest -> readWorkout(context, request)
			is UpdateWorkoutRequest -> updateWorkout(context, request)
			is DeleteWorkoutRequest -> deleteWorkout(context, request)
			is SearchWorkoutRequest -> searchWorkout(context, request)
			else -> throw DataNotAllowedException("Request is not Allowed", request)
		}
	} catch (e: Throwable) {
		errorAd(context, e)
	}

}