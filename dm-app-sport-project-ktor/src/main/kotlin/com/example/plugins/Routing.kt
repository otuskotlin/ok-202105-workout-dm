package com.example.plugins

import WorkoutCrud
import com.example.controllers.WorkoutController
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import service.WorkoutService

fun Application.configureRouting() {
	// Starting point for a Ktor app:

	val crud = WorkoutCrud()

	val workoutService = WorkoutService(crud)
	val workoutController = WorkoutController(workoutService)

	routing {
		route("workout") {
			post("create") {
				workoutController.create(call)
			}
			post("read") {
				workoutController.read(call)
			}
			post("update") {
				workoutController.update(call)
			}
			post("delete") {
				workoutController.delete(call)
			}
			post("search") {
				workoutController.search(call)
			}

		}
		get("/") {
			call.respondText("Hello World!")
		}
	}

}
