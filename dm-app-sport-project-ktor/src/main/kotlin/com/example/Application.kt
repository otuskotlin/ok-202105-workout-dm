package com.example

import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*


fun main() {
	embeddedServer(Tomcat, port = 8080, host = "localhost") {
		configureHTTP()
		configureRouting()
	}.start(wait = true)
}
