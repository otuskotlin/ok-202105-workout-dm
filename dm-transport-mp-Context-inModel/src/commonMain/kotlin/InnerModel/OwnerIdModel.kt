package InnerModel

value class OwnerIdModel(private val value: String) {

	companion object{
		val NONE = OwnerIdModel("")
	}

	override fun toString() = value
}
