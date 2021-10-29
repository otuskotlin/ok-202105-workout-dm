package chains

import ICorExec
import chain
import context.MpContext
import workers.chainInit
import workers.checkOperation
import workers.prepateAnswe
import workers.stub.workoutStub

object WorkoutCreate : ICorExec<MpContext> by chain<MpContext>({

	checkOperation("Проверка соответствия операции", MpContext.MpOperations.CREATE)

	chainInit(" Инициализация чейна")

	//validation
	workoutStub("Обработка стабкейса")

	//db worker
	prepateAnswe("Подготовка ответа")

}).build()

