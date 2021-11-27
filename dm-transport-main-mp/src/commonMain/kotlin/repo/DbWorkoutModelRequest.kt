package repo

import ModelForRequest.BaseWorkout

data class DbWorkoutModelRequest(
	var createWorkout: BaseWorkout
)
