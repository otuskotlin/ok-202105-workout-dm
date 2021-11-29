package repository

import ModelForRequest.ApiError
import ModelForRequest.CreateWorkout

data class DbWorkoutResponse (
	val result: CreateWorkout?,
	val isSuccess: Boolean,
	val errors: List<ApiError> = emptyList()
)
