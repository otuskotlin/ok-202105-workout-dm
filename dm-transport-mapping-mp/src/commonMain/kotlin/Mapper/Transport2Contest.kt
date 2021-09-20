package Mapper

import InnerModel.ExerciseModel
import InnerModel.OwnerIdModel
import InnerModel.PaginatedModel
import InnerModel.WorkoutIdModel
import InnerModel.WorkoutModel
import ModelForRequest.BasePaginatedRequest
import ModelForRequest.CreateWorkout
import ModelForRequest.ExerciseTransfer
import ModelForRequest.UpdateWorkout
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.DeleteWorkoutRequest
import ModelForRequest.cruds.ReadWorkoutRequest
import ModelForRequest.cruds.SearchWorkoutRequest
import ModelForRequest.cruds.UpdateWorkoutRequest
import context.MpContext


fun MpContext.setQuery(query: CreateWorkoutRequest) = apply {
	this.onRequest = query.requestId ?: ""
	this.requestWorkout = query.createWorkout?.toModel() ?: WorkoutModel()
}

fun MpContext.setQuery(query: ReadWorkoutRequest) = apply {
	this.onRequest = query.requestId ?: ""
	this.requestWorkoutId = WorkoutIdModel(query.workoutId ?: "")
}

fun MpContext.setQuery(query: UpdateWorkoutRequest) = apply {
	this.onRequest = query.requestId ?: ""
	this.requestWorkout = query.updateWorkout?.toModel() ?: WorkoutModel()
}

fun MpContext.setQuery(query: DeleteWorkoutRequest) = apply {
	onRequest = query.requestId ?: ""
	requestWorkoutId = WorkoutIdModel(query.deleteAdId ?: "")

}

fun MpContext.setQuery(query: SearchWorkoutRequest) = apply {
	onRequest = query.requestId ?: ""
	requestWorkoutId = WorkoutIdModel(query.searchWorkoutId ?: "")
	requestPage = query.page?.toModel() ?: PaginatedModel()
}

fun BasePaginatedRequest.toModel() = PaginatedModel(
	size = this.size ?: Int.MIN_VALUE,
	lastId = WorkoutIdModel(this.lastId ?: ""),
)

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

fun UpdateWorkout.toModel() = WorkoutModel(
	id = WorkoutIdModel(id ?: ""),
	ownerId = OwnerIdModel(ownerId ?: ""),
	name = name ?: "",
	description = description ?: "",
	items = this.items?.map { it.toModel() }?.toMutableList() ?: mutableListOf()
)