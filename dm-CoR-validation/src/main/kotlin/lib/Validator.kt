package lib

interface Validator<T> {
	fun validate(data: T): ValidationResult
}