package com.example.service

import context.MpContext

class WorkoutService {

	fun createWorkout(context: MpContext) : MpContext{
		val workoutForContext = context.requestWorkout
		return context.apply {
			responseWorkout = workoutForContext
		}
	}

	fun readWorkout(context: MpContext) : MpContext{
		context.responseWorkout = StubData.getModel()
		return context
	}
	fun updateWorkout(context: MpContext) : MpContext{
		context.responseWorkout = StubData.getModel()
		return context
	}
	fun deleteWorkout(context: MpContext) : MpContext{
		context.responseWorkout = StubData.getModel()
		return context
	}
	fun searchWorkout(context: MpContext) : MpContext{
		context.responseWorkouts = StubData.getModels().toMutableList()
		return context
	}

}