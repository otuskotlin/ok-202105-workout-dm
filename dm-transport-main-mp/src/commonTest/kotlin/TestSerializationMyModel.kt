import ModelForRequest.cruds.BaseMessage
import ModelForRequest.cruds.CreateWorkoutRequest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.test.Test
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

class TestSerializationMyModel {


    @Test
    fun Serialize1(){


        val message: BaseMessage = CreateWorkoutRequest("Create")
        val serializer = Json {
            serializersModule = SerializersModule {
                polymorphic(BaseMessage::class){
                    subclass(CreateWorkoutRequest::class)
                }
            }

        }
        val json = serializer.encodeToString(message)
        print(json)
        val messageClass = serializer.decodeFromString<BaseMessage>(json)
        print(messageClass::class)

    }


}