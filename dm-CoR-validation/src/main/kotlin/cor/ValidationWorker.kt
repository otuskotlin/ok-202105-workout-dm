package cor

import ICorChainDsl
import handlers.worker
import lib.ValidationResult
import lib.Validator

fun <T> ICorChainDsl<T>.validation(block: ValidationBuilder.() -> Unit){
	worker {
		title = "Валидация"
		description = "Валидация данных"


	}
}


class ValidationBuilder<C>{

	val errorHandler : C.(ValidationResult) ->  Unit = {}

	val validators = mutableListOf<Validator<C>>()

}