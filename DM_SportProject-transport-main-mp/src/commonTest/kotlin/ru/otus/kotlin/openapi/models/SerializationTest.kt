package ru.otus.kotlin.openapi.models

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.otus.kotlin.openapi.mappers.jsonSerializer
import ru.otus.kotlin.openapi.models.ru.otus.kotlin.openapi.models.Exercise
import kotlin.test.Test
import kotlin.test.assertTrue

class SerializationTest {


	@Test
	fun requestSerialTest(){
		val request = Exercise(
			"TestTren",
			10,
			3,
			false,
			10)

		val  jsonSerializer = Json {
			prettyPrint = true
			useAlternativeNames = true
			serializersModule = SerializersModule {
				polymorphic(Exercise::class){

				}
			}

		}
		val serialString = jsonSerializer.encodeToString(request)
		assertTrue { serialString.contains("TestTren") }
		print(serialString)
		print(Json.encodeToString(request))

	}
}