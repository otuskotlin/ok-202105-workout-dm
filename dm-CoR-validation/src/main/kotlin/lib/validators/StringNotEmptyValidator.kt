package lib.validators

import lib.ValidationResult
import lib.Validator
import lib.exception.impl.ValidationFieldError

class StringNotEmptyValidator(
	private val field: String = "",
	private val message: String = "field is null or empty"
) : Validator<String> {

	override infix fun validate(data: String): ValidationResult {
		return if (data.isBlank()) {
			ValidationResult(
				listOf(
					ValidationFieldError(
						message = message,
						field = field
					)
				)
			)
		}else{
			ValidationResult.SUCCESS
		}
	}
}