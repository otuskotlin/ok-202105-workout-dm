package service;

import Mapper.setQuery
import Mapper.toCreateResponse
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.CreateWorkoutResponse
import StubData
import WorkoutCrud
import context.MpContext

class WorkoutService(
	var crud: WorkoutCrud
) {

	suspend fun createWorkout(context: MpContext, request: CreateWorkoutRequest): CreateWorkoutResponse{
		crud.create(context.setQuery(request))
		return context.toCreateResponse()
	}

	fun readWorkout(context: MpContext): MpContext {
		context.responseWorkout = StubData.getModel()
		return context
	}

	fun updateWorkout(context: MpContext): MpContext {
		context.responseWorkout = StubData.getModel()
		return context
	}

	fun deleteWorkout(context: MpContext): MpContext {
		context.responseWorkout = StubData.getModel()
		return context
	}

	fun searchWorkout(context: MpContext): MpContext {
		context.responseWorkouts = StubData.getModels().toMutableList()
		return context
	}

}