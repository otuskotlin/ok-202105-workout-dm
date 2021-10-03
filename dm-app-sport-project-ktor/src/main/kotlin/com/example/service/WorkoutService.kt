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
		val workoutForContext = context.requestWorkout
		return context.apply {
			responseWorkout = workoutForContext
		}
	}
	fun updateWorkout(context: MpContext) : MpContext{
		val workoutForContext = context.requestWorkout
		return context.apply {
			responseWorkout = workoutForContext
		}
	}
	fun deleteWorkout(context: MpContext) : MpContext{
		val workoutForContext = context.requestWorkout
		return context.apply {
			responseWorkout = workoutForContext
		}
	}
	fun searchWorkout(context: MpContext) : MpContext{
		val workoutForContext = context.requestWorkout
		return context.apply {
			responseWorkout = workoutForContext
		}
	}


}