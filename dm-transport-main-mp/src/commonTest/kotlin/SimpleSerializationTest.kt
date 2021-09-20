import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.ru.otus.kotlin.kmp.transport.models.BaseMessage
import ru.ru.otus.kotlin.kmp.transport.models.WorkoutAddRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SimpleSerializationTest {

	@Test
	fun requestSerialTest(){
		val request = WorkoutAddRequest(
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
					subclass(WorkoutAddRequest::class, WorkoutAddRequest.serializer())
				}
			}
		}

		val dto:BaseMessage = WorkoutAddRequest(
			requestId = "12345"
		)
		val serializedString = jsonRequest.encodeToString(dto)
		val deserializationDto = jsonRequest.decodeFromString<BaseMessage>(serializedString)
		assertEquals(dto, deserializationDto)
	}


}