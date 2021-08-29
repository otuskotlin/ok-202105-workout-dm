import ru.ru.otus.kotlin.kmp.transport.models.Exercise
import ru.ru.otus.kotlin.kmp.transport.models.ExerciseTransfer


fun transferExercise2Exercise(et: ExerciseTransfer) = Exercise(
	nameExercise = et.nameExercise?: "",
	retry = et.retry?: Int.MIN_VALUE,
	numberRepetitions = et.numberRepetitions?: Int.MIN_VALUE,
	ownWeight = et.ownWeight?: true,
	weight = et.weight?: Int.MIN_VALUE
)