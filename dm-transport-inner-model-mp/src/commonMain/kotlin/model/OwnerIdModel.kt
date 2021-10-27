package model

import kotlin.jvm.JvmInline

@JvmInline
value class OwnerIdModel(private val value: String) {

	companion object{
		val NONE = OwnerIdModel("")
	}

	override fun toString() = value
}
