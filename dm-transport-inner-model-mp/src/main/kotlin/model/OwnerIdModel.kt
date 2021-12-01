package model

import java.util.*

@JvmInline
value class OwnerIdModel(private val id: String) {

    constructor(id: UUID) : this(id.toString())

    companion object {
        val NONE = OwnerIdModel("")
    }

    override fun toString() = id

    fun asUUID(): UUID = UUID.fromString(id)
}