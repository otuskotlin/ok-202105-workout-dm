package com.example.controllers

import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.DeleteWorkoutRequest
import ModelForRequest.cruds.ReadWorkoutRequest
import ModelForRequest.cruds.SearchWorkoutRequest
import ModelForRequest.cruds.UpdateWorkoutRequest
import context.MpContext
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import service.WorkoutService

class WorkoutController(private val workoutService: WorkoutService) {

	suspend fun create(call: ApplicationCall) {
		val request = call.receive<CreateWorkoutRequest>()
		val context = MpContext()
		val createWorkout = workoutService.createWorkout(context, request)
		call.respond(createWorkout)
	}

	suspend fun read(call: ApplicationCall) {
		val request = call.receive<ReadWorkoutRequest>()
		val context = MpContext()
		call.respond(workoutService.readWorkout(context, request))
	}

	suspend fun update(call: ApplicationCall) {
		val request = call.receive<UpdateWorkoutRequest>()
		val context = MpContext()
		call.respond(workoutService.updateWorkout(context, request))
	}

	suspend fun delete(call: ApplicationCall) {
		val request = call.receive<DeleteWorkoutRequest>()
		val context = MpContext()
		call.respond(workoutService.deleteWorkout(context, request))
	}

	suspend fun search(call: ApplicationCall) {
		val request = call.receive<SearchWorkoutRequest>()
		val context = MpContext()
		call.respond(workoutService.searchWorkout(context, request))
	}
}