package kafka

import ModelForRequest.cruds.BaseMessage
import ModelForRequest.jsonRequest
import context.MpContext
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.errors.WakeupException
import java.time.Duration
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class AppKafkaConsumer(private val config: AppKafkaConfig) {
	private val consumer = config.kafkaConsumer
	private val producer = config.kafkaProducer
	private val service = config.service
	private val process = AtomicBoolean(true)

	fun run() = runBlocking {
		try {
			consumer.subscribe(listOf(config.kafkaTopicIn))
			while (process.get()) {
				val ctx = MpContext(
//                    startTime = Instant.now(),
				)
				try {
					val records: ConsumerRecords<String, String> = consumer.poll(Duration.ofSeconds(1))
					records.forEach { record: ConsumerRecord<String, String> ->
						val request = jsonRequest.decodeFromString<BaseMessage>(record.value())
						sendResponse(service.handleWorkout(ctx, request))
					}
				} catch (e: Throwable) {
					sendResponse(service.errorAd(ctx, e))
				}
			}
		} catch (ex: WakeupException) {
			// ignore for shutdown
		} catch (ex: RuntimeException) {
			// exception handling
			withContext(NonCancellable) {
				throw ex
			}
		} finally {
			withContext(NonCancellable) {
				consumer.close()
			}
		}
	}

	private suspend fun sendResponse(response: BaseMessage) {
		val json = jsonRequest.encodeToString(response)
		val resRecord = ProducerRecord(
			config.kafkaTopicOut,
			UUID.randomUUID().toString(),
			json
		)
		producer.send(resRecord)
	}

	fun stop() {
		process.set(false)
	}
}
