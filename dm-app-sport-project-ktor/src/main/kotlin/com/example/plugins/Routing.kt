package com.example.plugins

import ModelForRequest.cruds.CreateWorkoutRequest
import com.example.controllers.WorkoutController
import com.example.service.WorkoutService
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
	// Starting point for a Ktor app:

	val workoutService = WorkoutService()
	val workoutController = WorkoutController(workoutService)

	routing {
		route("workout"){
			post("create"){
				workoutController.create(call)
			}
			post("read"){
				workoutController.read(call)
			}
			post("update"){
				workoutController.update(call)
			}
			post("delete"){
				workoutController.delete(call)
			}
			post("search"){
				workoutController.search(call)
			}


		}
		get("/") {
			call.respondText("Hello World!")
		}
	}

}
