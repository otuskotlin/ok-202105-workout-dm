import ru.ru.otus.kotlin.kmp.transport.models.Exercise
import ru.ru.otus.kotlin.kmp.transport.models.ExerciseTransfer
import kotlin.test.Test
import kotlin.test.assertTrue

class MapperTest {


	@Test
	fun ExerciseTransferToExercise(){
		val exerciseTransfer = ExerciseTransfer("test", 10, 10, true, 10)
		val exercise = Exercise("test", 10, 10, true, 10)
		assertTrue { exercise == transferExercise2Exercise(exerciseTransfer) }
	}

}