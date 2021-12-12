package validators.workers

import context.CorStatus
import context.MpContext
import handlers.CorChainDsl
import handlers.worker

internal fun CorChainDsl<MpContext>.checkOperation(
    title: String,
    targetOperation: MpContext.MpOperations
) =
    worker {
        this.title = title
        description = "Проверка соответствия"
        on {
            operation != targetOperation
        }
        handle {
            status = CorStatus.FAILING
            println("check operation FAILING")
            addError(
                e = Exception("Expected ${targetOperation} but was ${operation}")
            )

        }
    }