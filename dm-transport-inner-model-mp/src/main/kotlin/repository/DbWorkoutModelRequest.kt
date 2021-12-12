package repository

import ModelForRequest.BaseWorkout
import model.WorkoutModel

data class DbWorkoutModelRequest(
	var workoutModel: WorkoutModel
)
