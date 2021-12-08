package chains

import ICorExec
import chain
import context.CorStatus
import context.MpContext
import model.CommonErrorModel
import validation
import validators.workers.*
import validators.workers.chainInit
import validators.workers.checkOperation
import validators.workers.prepareAnswer
import validators.workers.stub.workoutStub

object WorkoutUpdate : ICorExec<MpContext> by chain<MpContext>({

	checkOperation("Проверка соответствия операции", MpContext.MpOperations.CREATE)

	chainInit(" Инициализация чейна")

	validation {
		errorHandler { validationResult ->
			if (validationResult.isSuccess) return@errorHandler
			val errs = validationResult.errors.map {
				CommonErrorModel(message = it.message)
			}
			errors.addAll(errs)
			status = CorStatus.FAILING
		}

		checkIdRequest()
	}

	workoutStub("Обработка стабкейса")
	choseBd("Установка репозитория")
	//db worker
	prepareAnswer("Подготовка ответа")

}).build()
