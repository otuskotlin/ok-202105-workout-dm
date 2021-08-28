import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import ru.ru.otus.kotlin.openapi.models.Exercise
import kotlin.test.assertTrue

class SimpleTest {

	val objcetMapper = ObjectMapper()

	@Test
	fun createEntity() {
		val exercise = Exercise("Test", 10, 1, false, 10)
		print(exercise)
	}

	@Test
	fun testSerializationText() {
		val exercise = Exercise("Test", 10, 1, false, 10)
		val message = objcetMapper.writeValueAsString(exercise)
		print(
			"json = $message \n" +
					"object = $exercise"
		)

		assertTrue {
			message.contains("Test")
		}
	}

	@Test
	fun testSerialization() {
		val exercise = Exercise("Test", 10, 1, false, 10)
		val exerciseJson = objcetMapper.writeValueAsString(exercise)
		print(exerciseJson)
		val eadValue = objcetMapper.readValue(exerciseJson, Exercise::class.java) as Exercise
		assertTrue { exercise == eadValue }
	}
}
