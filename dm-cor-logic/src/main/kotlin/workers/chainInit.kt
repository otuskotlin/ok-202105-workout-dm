package workers

import context.CorStatus
import context.MpContext
import handlers.CorChainDsl
import handlers.worker

internal fun CorChainDsl<MpContext>.chainInit(title: String) =
	worker {
		this.title = title
		on { status == CorStatus.NONE }
		handle {
			status = CorStatus.RUNNING
		}
	}
