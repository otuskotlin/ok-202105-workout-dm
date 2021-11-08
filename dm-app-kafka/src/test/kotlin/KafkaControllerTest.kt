import ModelForRequest.CreateWorkout
import ModelForRequest.Debug
import ModelForRequest.cruds.BaseMessage
import ModelForRequest.cruds.CreateWorkoutRequest
import ModelForRequest.cruds.CreateWorkoutResponse
import ModelForRequest.cruds.ReadWorkoutRequest
import ModelForRequest.cruds.UpdateWorkoutRequest
import kafka.AppKafkaConfig
import kafka.AppKafkaConsumer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class KafkaControllerTest {
	val jsonRequest = Json {
		prettyPrint = true
		serializersModule = SerializersModule {
			polymorphic(BaseMessage::class) {
				subclass(CreateWorkoutRequest::class, CreateWorkoutRequest.serializer())
				subclass(UpdateWorkoutRequest::class, UpdateWorkoutRequest.serializer())
				subclass(ReadWorkoutRequest::class, ReadWorkoutRequest.serializer())
			}
		}
	}

	@Test
	fun runKafka() {
		val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
		val producer = MockProducer<String, String>(true, StringSerializer(), StringSerializer())
		val config = AppKafkaConfig(
			kafkaConsumer = consumer,
			kafkaProducer = producer,
		)
		val app = AppKafkaConsumer(config)
		consumer.schedulePollTask {
			consumer.rebalance(Collections.singletonList(TopicPartition(config.kafkaTopicIn, 0)))
			val string = CreateWorkoutRequest(
//				messageType = "create",
				requestId = "123",
				debug = Debug(
					Debug.Mode.STUB,
					stubCase = Debug.StubCase.SUCCESS
				),
				createWorkout = CreateWorkout(
					id = "1234",
					ownerId = "123",
					name = "test",
					description = "Some workout",
					items = listOf()
				),
				status = null
			).toJson()
			consumer.addRecord(
				ConsumerRecord(
					config.kafkaTopicIn,
					PARTITION,
					0L,
					"test-1",
					string
				)
			)
			app.stop()
		}

		val startOffsets: MutableMap<TopicPartition, Long> = mutableMapOf()
		val tp = TopicPartition(config.kafkaTopicIn, PARTITION)
		startOffsets[tp] = 0L
		consumer.updateBeginningOffsets(startOffsets)

		app.run()

		val message = producer.history().first()
		val result = jsonRequest.decodeFromString<CreateWorkoutResponse>(message.value())
		assertEquals("123", result.requestId)
		assertEquals("Some workout", result.createdWorkout?.description)
	}

	companion object {
		const val PARTITION = 0
	}

	private fun CreateWorkoutRequest.toJson(): String = jsonRequest.encodeToString(this)

}

