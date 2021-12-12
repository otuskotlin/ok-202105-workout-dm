package model

import java.util.*

@JvmInline
value class WorkoutIdModel(private val id: String) {

	constructor(id: UUID) : this(id.toString())

	companion object {
		val NONE = WorkoutIdModel("")
	}

	fun asString() = id

	fun asUUID(): UUID = UUID.fromString(id)
}
