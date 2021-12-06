package repository

import model.WorkoutIdModel

data class DbWorkoutIdRequest(
	val id: WorkoutIdModel = WorkoutIdModel.NONE
)
