package Mapper

import model.ExerciseModel
import model.IError
import model.OwnerIdModel
import model.WorkoutIdModel
import model.WorkoutModel
import ModelForRequest.ApiError
import ModelForRequest.ExerciseTransfer
import ModelForRequest.ResponseWorkout
import ModelForRequest.cruds.BaseResponse
import ModelForRequest.cruds.CreateWorkoutResponse
import ModelForRequest.cruds.DeleteWorkoutResponse
import ModelForRequest.cruds.ReadWorkoutResponse
import ModelForRequest.cruds.SearchWorkoutResponse
import ModelForRequest.cruds.UpdateWorkoutResponse
import context.MpContext

fun MpContext.toCreateResponse() = CreateWorkoutResponse(
	requestId = this.idRequest.takeIf { it.isNotBlank() },
	createdWorkout = responseWorkout.takeIf { it != WorkoutModel() }?.toTransport(),
	errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
	result = if (errors.find { it.level == IError.Level.ERROR } == null) BaseResponse.Result.SUCCESS
	else BaseResponse.Result.ERROR
)

fun MpContext.toReadResponse() = ReadWorkoutResponse(
	requestId = this.idRequest.takeIf { it.isNotBlank() },
	readWorkout = responseWorkout.takeIf { it != WorkoutModel() }?.toTransport(),
	errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
	result = if (errors.find { it.level == IError.Level.ERROR } == null) BaseResponse.Result.SUCCESS
	else BaseResponse.Result.ERROR
)

fun MpContext.toUpdateResponse() = UpdateWorkoutResponse(
	requestId = this.idRequest.takeIf { it.isNotBlank() },
	updatedAd = responseWorkout.takeIf { it != WorkoutModel() }?.toTransport(),
	errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
	result = if (errors.find { it.level == IError.Level.ERROR } == null) BaseResponse.Result.SUCCESS
	else BaseResponse.Result.ERROR
)

fun MpContext.toDeleteResponse() = DeleteWorkoutResponse(
	requestId = this.idRequest.takeIf { it.isNotBlank() },
	deletedAd = responseWorkout.takeIf { it != WorkoutModel() }?.toTransport(),
	errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
	result = if (errors.find { it.level == IError.Level.ERROR } == null) BaseResponse.Result.SUCCESS
	else BaseResponse.Result.ERROR
)

fun MpContext.toSearchResponse() = SearchWorkoutResponse(
	requestId = this.idRequest.takeIf { it.isNotBlank() },
	foundAds = responseWorkouts.takeIf { it.isNotEmpty() }?.filter { it != WorkoutModel() }?.map { it.toTransport() },
	errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
	result = if (errors.find { it.level == IError.Level.ERROR } == null) BaseResponse.Result.SUCCESS
	else BaseResponse.Result.ERROR
)

private fun WorkoutModel.toTransport() = ResponseWorkout(
	id = id.takeIf { it != WorkoutIdModel.NONE }?.asString(),
	name = name.takeIf { it.isNotEmpty() },
	description = description.takeIf { it.isNotEmpty() },
	items = items.takeIf { it.isNotEmpty() }?.map { it.toTransport() }?.toMutableList(),
	ownerId = ownerId.takeIf { it != OwnerIdModel.NONE }?.toString()
)

private fun IError.toTransport() = ApiError(
	message = message.takeIf { it.isNotBlank() },
	field = field.takeIf { it.isNotBlank() },
)

private fun ExerciseModel.toTransport() = ExerciseTransfer(
	nameExercise = nameExercise.takeIf { nameExercise.isNotBlank() },
	retry = retry,
	numberRepetitions = numberRepetitions,
	ownWeight = ownWeight,
	weight = weight
)



