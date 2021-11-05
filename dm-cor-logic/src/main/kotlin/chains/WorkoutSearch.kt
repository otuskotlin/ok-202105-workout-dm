package chains

import ICorExec
import chain
import context.MpContext
import validators.workers.chainInit
import validators.workers.checkOperation
import validators.workers.prepareAnswer
import validators.workers.stub.workoutStub

object WorkoutSearch : ICorExec<MpContext> by chain<MpContext>({

	checkOperation("Проверка соответствия операции", MpContext.MpOperations.CREATE)

	chainInit(" Инициализация чейна")

	//validation
	workoutStub("Обработка стабкейса")

	//db worker
	prepareAnswer("Подготовка ответа")

}).build()