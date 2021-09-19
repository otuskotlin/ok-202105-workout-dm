package InnerModel

data class WorkoutModel(
	var id: WorkoutIdModel = WorkoutIdModel.NONE,
	var ownerId : OwnerIdModel = OwnerIdModel.NONE,
	var name: String = "",
	var description: String = "",
	var items: MutableList<ExerciseModel> = mutableListOf()
)