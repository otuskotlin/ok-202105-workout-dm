package ru.otus.kotlin.openapi.mappers

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.otus.kotlin.openapi.models.ru.otus.kotlin.openapi.models.Exercise

val  jsonSerializer = Json {
	prettyPrint = true
	useAlternativeNames = true
	serializersModule = SerializersModule {
		polymorphic(Exercise::class)
	}

}