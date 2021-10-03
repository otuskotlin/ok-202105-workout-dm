package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
	embeddedServer(Netty, port = 8080, host = "localhost") {
		configureHTTP()
		configureRouting()
	}.start(wait = true)
}