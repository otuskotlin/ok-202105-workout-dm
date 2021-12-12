package ModelForRequest

import ModelForRequest.cruds.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val jsonRequest = Json {
	prettyPrint = true
	serializersModule = SerializersModule {
		polymorphic(BaseMessage::class) {
			subclass(CreateWorkoutRequest::class, CreateWorkoutRequest.serializer())
			subclass(ReadWorkoutRequest::class, ReadWorkoutRequest.serializer())
			subclass(UpdateWorkoutRequest::class, UpdateWorkoutRequest.serializer())
			subclass(DeleteWorkoutRequest::class, DeleteWorkoutRequest.serializer())
			subclass(SearchWorkoutRequest::class, SearchWorkoutRequest.serializer())

			subclass(CreateWorkoutResponse::class, CreateWorkoutResponse.serializer())
			subclass(ReadWorkoutResponse::class, ReadWorkoutResponse.serializer())
			subclass(UpdateWorkoutResponse::class, UpdateWorkoutResponse.serializer())
			subclass(DeleteWorkoutResponse::class, DeleteWorkoutResponse.serializer())
			subclass(SearchWorkoutResponse::class, SearchWorkoutResponse.serializer())
		}
	}
}
