import ModelForRequest.cruds.BaseMessage
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.UpdateWorkoutRequest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SimpleSerializationTest {

	@Test
	fun requestSerialTest(){
		val request = CreateWorkoutRequest(
			requestId = "12345"
		)
		val json = Json {
			prettyPrint= true
		}
		val serialString = json.encodeToString(request)
		assertTrue { serialString.contains("12345") }
	}

	@Test
	fun serialize(){
		val jsonRequest = Json{
			prettyPrint = true
			serializersModule = SerializersModule {
				polymorphic(BaseMessage::class){
					subclass(CreateWorkoutRequest::class, CreateWorkoutRequest.serializer())
					subclass(UpdateWorkoutRequest::class, UpdateWorkoutRequest.serializer())
				}
			}
		}

		val dtoCreate:BaseMessage = CreateWorkoutRequest(requestId = "1")
		val dtoUpdate:BaseMessage = UpdateWorkoutRequest(requestId = "1")


		val serializedCreate = jsonRequest.encodeToString(dtoCreate)
		val serializedUpdate = jsonRequest.encodeToString(dtoUpdate)
		println(serializedCreate)
		println(serializedUpdate)

		val deserialCreate = jsonRequest.decodeFromString<BaseMessage>(serializedCreate)
		val deserialUpdate = jsonRequest.decodeFromString<BaseMessage>(serializedUpdate)

		println(deserialCreate::class)
		println(deserialUpdate::class)

	}


}