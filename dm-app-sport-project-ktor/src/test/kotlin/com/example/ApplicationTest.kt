package com.example

import com.example.plugins.configureHTTP
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test

class ApplicationTest {

	@Test
	fun `simple test`(){
		withTestApplication(Application::configureHTTP){
			handleRequest(HttpMethod.Post, "/workout/create" ){

			}
		}
	}

}