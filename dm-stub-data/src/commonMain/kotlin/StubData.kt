import InnerModel.ExerciseModel
import InnerModel.OwnerIdModel
import InnerModel.WorkoutIdModel
import InnerModel.WorkoutModel

object StubData {


	private val stubExerciseModel1 = ExerciseModel(
		nameExercise = "Прыжки",
		retry = 10,
		numberRepetitions = 3,
		ownWeight = true,
		weight = 0
	)

	private val stubExerciseModel2 = ExerciseModel(
		nameExercise = "Отжимания",
		retry = 3,
		numberRepetitions = 5,
		ownWeight = false,
		weight = 100
	)

	private val stubWorkoutModel = WorkoutModel(
		id = WorkoutIdModel(id = "99"),
		ownerId = OwnerIdModel(value = "10"),
		name = "Простая тренировка",
		description = "Это просто стабовые данные",
		items = mutableListOf(stubExerciseModel1, stubExerciseModel2)
	)

	fun getModel(model: (WorkoutModel.() -> Unit)? = null) = stubWorkoutModel.also { stub ->
		model?.let { stub.apply(it) }
	}

	fun getModels() = mutableListOf(stubWorkoutModel)
	

}