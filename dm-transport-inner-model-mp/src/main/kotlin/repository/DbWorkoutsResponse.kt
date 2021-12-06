package repository

import ModelForRequest.ApiError
import model.WorkoutModel

data class DbWorkoutsResponse(

	val result: MutableList<WorkoutModel>,
	val isSuccess: Boolean,
	val errors: List<ApiError> = emptyList()

)