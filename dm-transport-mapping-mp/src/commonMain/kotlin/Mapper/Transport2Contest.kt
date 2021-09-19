package Mapper

import InnerModel.ExerciseModel
import InnerModel.OwnerIdModel
import InnerModel.WorkoutIdModel
import InnerModel.WorkoutModel
import ModelForRequest.CreateWorkout
import ModelForRequest.ExerciseTransfer
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.ReadWorkoutRequest
import context.MpContext


fun MpContext.setQuery(query: CreateWorkoutRequest) = apply {
	this.onRequest = query.requestId?: ""
	this.requestWorkout = query.createWorkout?.toModel() ?: WorkoutModel()
}

fun MpContext.setQuery(query: ReadWorkoutRequest) = apply {
	this.onRequest = query.requestId?:""
	this.requestWorkoutId = WorkoutIdModel(query.workoutId?:"")

}

fun CreateWorkout.toModel() = WorkoutModel(
	description = this.description ?: "",
	ownerId = OwnerIdModel(this.ownerId ?: ""),
	name = this.name ?: "",
	items = this.items?.map { it.toModel() }?.toMutableList() ?: mutableListOf()
)

fun ExerciseTransfer.toModel() = ExerciseModel(
	nameExercise = this.nameExercise ?: "",
	retry = this.retry ?: Int.MIN_VALUE,
	numberRepetitions = numberRepetitions ?: Int.MIN_VALUE,
	ownWeight = ownWeight ?: true,
	weight = this.weight ?: Int.MIN_VALUE
)