package Mapper

import model.ExerciseModel
import model.OwnerIdModel
import model.PaginatedModel
import model.WorkoutIdModel
import model.WorkoutModel
import ModelForRequest.BasePaginatedRequest
import ModelForRequest.CreateWorkout
import ModelForRequest.Debug
import ModelForRequest.ExerciseTransfer
import ModelForRequest.UpdateWorkout
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.DeleteWorkoutRequest
import ModelForRequest.cruds.ReadWorkoutRequest
import ModelForRequest.cruds.SearchWorkoutRequest
import ModelForRequest.cruds.UpdateWorkoutRequest
import context.MpContext
import model.MpStubCases

fun MpContext.setQuery(query: CreateWorkoutRequest) = apply {
	operation = MpContext.MpOperations.CREATE
	idRequest = query.requestId ?: ""
	requestWorkout = query.createWorkout?.toModel() ?: WorkoutModel()
	mpStubCases = query.debug?.stubCase.toModel()
}

fun MpContext.setQuery(query: ReadWorkoutRequest) = apply {
	operation = MpContext.MpOperations.READ
	idRequest = query.requestId ?: ""
	requestWorkoutId = WorkoutIdModel(query.workoutId ?: "")
	mpStubCases = query.debug?.stubCase.toModel()
}

fun MpContext.setQuery(query: UpdateWorkoutRequest) = apply {
	operation = MpContext.MpOperations.UPDATE
	idRequest = query.requestId ?: ""
	requestWorkout = query.updateWorkout?.toModel() ?: WorkoutModel()
	mpStubCases = query.debug?.stubCase.toModel()
}

fun MpContext.setQuery(query: DeleteWorkoutRequest) = apply {
	this.operation = MpContext.MpOperations.DELETE
	idRequest = query.requestId ?: ""
	requestWorkoutId = WorkoutIdModel(query.deleteAdId ?: "")
	mpStubCases = query.debug?.stubCase.toModel()
}

fun MpContext.setQuery(query: SearchWorkoutRequest) = apply {
	this.operation = MpContext.MpOperations.SEARCH
	idRequest = query.requestId ?: ""
	requestWorkoutId = WorkoutIdModel(query.searchWorkoutId ?: "")
	requestPage = query.page?.toModel() ?: PaginatedModel()
	mpStubCases = query.debug?.stubCase.toModel()
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

private fun Debug.StubCase?.toModel() = when(this) {
	Debug.StubCase.SUCCESS -> MpStubCases.SUCCESS
	Debug.StubCase.DATABASE_ERROR -> MpStubCases.DATABASE_ERROR
	null -> MpStubCases.NONE
}