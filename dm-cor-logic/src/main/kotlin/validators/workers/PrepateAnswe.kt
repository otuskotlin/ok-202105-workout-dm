package validators.workers

import context.CorStatus
import context.MpContext
import handlers.CorChainDsl
import handlers.chain
import handlers.worker

internal fun CorChainDsl<MpContext>.prepareAnswer(title: String) {
	chain {
		this.title = title
		worker {
			this.title = "Успешный процесс"
			on { status in setOf(CorStatus.RUNNING, CorStatus.FINISHING) }
			handle {
				status = CorStatus.SUCCESS
			}
		}
		worker {
			this.title = "Неуспешный процесс"
			on { status != CorStatus.SUCCESS }
			handle {
				status = CorStatus.ERROR
				addError(
					e = Exception("No matching validators.workers for stubCase: ${mpStubCases}")
				)
			}
		}
	}
}
