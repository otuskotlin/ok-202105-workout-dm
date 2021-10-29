package workers

import context.CorStatus
import context.MpContext
import handlers.CorChainDsl
import handlers.worker

internal fun CorChainDsl<MpContext>.checkOperation(title: String, targetOperation: MpContext.MpOperations) =
	worker {
		this.title = title
		description = "Проверка соответствия"
		on {
			operation != MpContext.MpOperations.CREATE
		}
		handle {
			status = CorStatus.FAILING
			addError(
				e = Exception("Expected ${targetOperation} but was ${operation}")
			)

		}
	}