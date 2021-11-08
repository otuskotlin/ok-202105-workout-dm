import kafka.AppKafkaConfig
import kafka.AppKafkaConsumer

fun main(){

	val config = AppKafkaConfig()
	val consumer = AppKafkaConsumer(config)
	consumer.run()

}