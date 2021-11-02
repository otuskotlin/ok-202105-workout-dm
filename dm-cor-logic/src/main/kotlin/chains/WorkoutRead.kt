package chains

import ICorExec
import chain
import context.MpContext
import workers.chainInit
import workers.checkOperation
import workers.prepareAnswer
import workers.stub.workoutStub

object WorkoutRead: ICorExec<MpContext> by chain<MpContext> ({

	checkOperation("Проверка соответствия операции", MpContext.MpOperations.READ)

	chainInit(" Инициализация чейна")

	//validation
	workoutStub("Обработка стабкейса")

	//db worker
	prepareAnswer("Подготовка ответа")

}).build()