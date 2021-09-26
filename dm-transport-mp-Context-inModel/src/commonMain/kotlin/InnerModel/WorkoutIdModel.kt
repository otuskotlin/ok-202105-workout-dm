package InnerModel

import kotlin.jvm.JvmInline

@JvmInline
value class WorkoutIdModel(private val id: String){
	companion object{
		val NONE = WorkoutIdModel("")
	}
	fun asString() = id

}
