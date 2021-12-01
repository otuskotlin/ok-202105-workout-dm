package model

data class ExerciseModel(
	var nameExercise: String = "",
	/* количество повторов */
	var retry: Int = Int.MIN_VALUE,
	/* количество повторений */
	var numberRepetitions: Int = Int.MIN_VALUE,
	/* упражнение с своим весом? */
	var ownWeight: Boolean = true,
	/* вес в упражнении */
	var weight: Int = Int.MIN_VALUE
)