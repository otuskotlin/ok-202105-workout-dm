package chains

import ICorExec
import StubData
import chain
import context.CorStatus
import context.MpContext
import handlers.chain
import handlers.worker
import model.MpStubCases

object WorkoutCreate : ICorExec<MpContext> by chain<MpContext>({

	worker {
		title = "Проверка соответствия операции"
		on {
			operation != MpContext.MpOperations.CREATE
		}
		handle {
			status = CorStatus.FAILING
		}
	}

	worker {
		title = " Инициализация чейна"
		on { status == CorStatus.NONE }
		handle {
			status = CorStatus.RUNNING
		}
	}

	//validation
	chain {
		title = "Обработка стабкейса"
		on { status == CorStatus.RUNNING && mpStubCases != MpStubCases.NONE }
		worker {
			title = "SUCCESS stubcase worker"
			on { mpStubCases == MpStubCases.SUCCESS }
			handle {
				responseWorkout = StubData.getModel()
				status = CorStatus.FINISHING
			}
		}
		worker {
			title = "Когда не подоходящего стаб кейса"
			on { status == CorStatus.RUNNING }
			handle {
				status = CorStatus.FAILING
			}
		}


	}


	//db worker
	chain {
		title = "Подготовка ответа"
		worker {
			title = "Успешный процесс"
			on { status in setOf(CorStatus.RUNNING, CorStatus.FINISHING) }
			handle {
				status = CorStatus.SUCCESS
			}
		}
		worker {
			title = "Неуспешный процесс"
			on { status != CorStatus.SUCCESS }
			handle {
				status = CorStatus.ERROR
			}
		}

	}


}).build()