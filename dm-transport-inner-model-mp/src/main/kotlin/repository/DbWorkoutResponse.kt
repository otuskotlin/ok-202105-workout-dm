package repository

import ModelForRequest.ApiError
import ModelForRequest.CreateWorkout
import model.WorkoutModel

data class DbWorkoutResponse (
	val result: WorkoutModel?,
	val isSuccess: Boolean,
	val errors: List<ApiError> = emptyList()
)
