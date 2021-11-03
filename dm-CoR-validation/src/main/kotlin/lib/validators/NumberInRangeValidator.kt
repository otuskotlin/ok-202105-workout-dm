package lib.validators

import lib.ValidationResult
import lib.Validator
import lib.exception.impl.ValidationFieldError

class NumberInRangeValidator<T : Comparable<T>>(
	private val field: String,
	private val message: String = "Not in range",
	private val min: T,
	private val max: T

) : Validator<T> {
	override fun validate(data: T): ValidationResult {
		return if (data in min..max) {
			ValidationResult.SUCCESS
		} else {
			ValidationResult(
				listOf(
					ValidationFieldError(
						field = field,
						message = message
					)
				)
			)
		}
	}
}