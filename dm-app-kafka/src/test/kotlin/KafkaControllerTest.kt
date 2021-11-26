import ModelForRequest.CreateWorkout
import ModelForRequest.Debug
import ModelForRequest.cruds.*
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
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class KafkaControllerTest {
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

	@Test
	fun runKafka() {
		val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
		val producer = MockProducer<String, String>(true, StringSerializer(), StringSerializer())
		val config = AppKafkaConfig(
			kafkaConsumer = consumer,
			kafkaProducer = producer,
		)
		val createWorkout = CreateWorkoutRequest(
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
		)
		val createWorkoutJson = createWorkout.toJson()

		val app = AppKafkaConsumer(config)
		consumer.schedulePollTask {
			consumer.rebalance(Collections.singletonList(TopicPartition(config.kafkaTopicIn, 0)))

			consumer.addRecord(
				ConsumerRecord(
					config.kafkaTopicIn,
					PARTITION,
					0L,
					"test-1",
					createWorkoutJson
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
		val jsonTest = message.value()
		val result = jsonRequest.decodeFromString<BaseMessage>(jsonTest)
		assertNotNull(result)
		assertTrue(result is CreateWorkoutResponse)
		assertEquals(createWorkout.requestId ,result.requestId)
	}

	companion object {
		const val PARTITION = 0
	}

	private fun BaseMessage.toJson(): String = jsonRequest.encodeToString(this)

}

