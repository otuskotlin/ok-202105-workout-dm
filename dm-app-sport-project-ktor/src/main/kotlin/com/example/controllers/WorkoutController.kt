package com.example.controllers

import Mapper.setQuery
import Mapper.toCreateResponse
import Mapper.toDeleteResponse
import Mapper.toReadResponse
import Mapper.toSearchResponse
import Mapper.toUpdateResponse
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.DeleteWorkoutRequest
import ModelForRequest.cruds.ReadWorkoutRequest
import ModelForRequest.cruds.SearchWorkoutRequest
import ModelForRequest.cruds.UpdateWorkoutRequest
import com.example.service.WorkoutService
import context.MpContext
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

class WorkoutController(private val workoutService: WorkoutService) {

	suspend fun create(call: ApplicationCall) {
		val createWorkoutRequest = call.receive<CreateWorkoutRequest>()
		val context = MpContext().setQuery(createWorkoutRequest)
		call.respond(workoutService.createWorkout(context).toCreateResponse())
	}

	suspend fun read(call: ApplicationCall) {
		val createWorkoutRequest = call.receive<ReadWorkoutRequest>()
		val context = MpContext().setQuery(createWorkoutRequest)
		call.respond(workoutService.readWorkout(context).toReadResponse())
	}
	suspend fun update(call: ApplicationCall) {
		val createWorkoutRequest = call.receive<UpdateWorkoutRequest>()
		val context = MpContext().setQuery(createWorkoutRequest)
		call.respond(workoutService.updateWorkout(context).toUpdateResponse())
	}
	suspend fun delete(call: ApplicationCall) {
		val createWorkoutRequest = call.receive<DeleteWorkoutRequest>()
		val context = MpContext().setQuery(createWorkoutRequest)
		call.respond(workoutService.deleteWorkout(context).toDeleteResponse())
	}
	suspend fun search(call: ApplicationCall) {
		val createWorkoutRequest = call.receive<SearchWorkoutRequest>()
		val context = MpContext().setQuery(createWorkoutRequest)
		call.respond(workoutService.searchWorkout(context).toSearchResponse())
	}
}