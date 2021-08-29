import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.ru.otus.kotlin.kmp.transport.models.ExerciseTransfer
import kotlin.test.Test
import kotlin.test.assertTrue

class SimpleTest {


	@Test
	fun simpleTest() {
		val exercise = ExerciseTransfer("Test", 1, 10, true, 10)
		println(exercise)

		val json = Json {
			prettyPrint = true

		}
		val serializableString = json.encodeToString(exercise)

		println(serializableString)

		val newExercise = json.decodeFromString<ExerciseTransfer>(serializableString)

		assertTrue { exercise == newExercise }
	} 


}

