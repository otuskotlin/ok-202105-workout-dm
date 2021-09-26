import InnerModel.ExerciseModel
import InnerModel.IUserSession
import InnerModel.OwnerIdModel
import InnerModel.WorkoutIdModel
import InnerModel.WorkoutModel
import context.MpContext

fun getTestData(): MpContext {
	return MpContext(
		userSession = IUserSession.Companion.EmptySession,
		onRequest = "",
		requestWorkoutId = WorkoutIdModel(id = "10"),
		responseWorkout = getWorkoutModel(),
	)
}

fun getWorkoutModel() = WorkoutModel(
	id = WorkoutIdModel(id = "5"),
	ownerId = OwnerIdModel(value = ""),
	name = "TestWorkoutModel",
	description = "Structor for test",
	items = mutableListOf(getJump(), getPush())
)

fun getJump() = ExerciseModel(
	nameExercise = "Jump",
	retry = 5,
	numberRepetitions = 10,
	ownWeight = true,
	weight = 0
)

fun getPush() = ExerciseModel(
	nameExercise = "push grif",
	retry = 3,
	numberRepetitions = 10,
	ownWeight = false,
	weight = 20
)
