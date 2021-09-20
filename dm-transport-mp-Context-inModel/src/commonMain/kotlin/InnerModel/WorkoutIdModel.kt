package InnerModel

value class WorkoutIdModel(private val id: String){
	companion object{
		val NONE = WorkoutIdModel("")
	}
	fun asString() = id

}
