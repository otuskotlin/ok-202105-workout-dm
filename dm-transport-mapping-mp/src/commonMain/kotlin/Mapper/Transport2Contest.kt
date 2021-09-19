package Mapper

import ModelForRequest.cruds.CreateWorkoutRequest
import context.MpContext


fun MpContext.setQuery(query : CreateWorkoutRequest) = apply {
	this.requestWorkout = query.createWorkout?:
}