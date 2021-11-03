package lib.exception.impl

import lib.exception.ValidationError

class ValidationFieldError(
	override val message: String = "",
	val field: String = ""
) : ValidationError {

}