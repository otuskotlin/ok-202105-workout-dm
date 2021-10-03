package com.example.plugins

import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.configureHTTP() {
	install(DefaultHeaders)//Для стандартных заголовков, почитать бы об этом.
	install(CallLogging)
	install(AutoHeadResponse)
	install(ContentNegotiation){
		json(Json{
			ignoreUnknownKeys = true
			prettyPrint = true
			isLenient = true
		})
	}

	install(CORS) {
		method(HttpMethod.Options)
		method(HttpMethod.Put)
		method(HttpMethod.Delete)
		method(HttpMethod.Patch)
		header(HttpHeaders.Authorization)
		header("MyCustomHeader")
		allowCredentials = true
		anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
	}

}
