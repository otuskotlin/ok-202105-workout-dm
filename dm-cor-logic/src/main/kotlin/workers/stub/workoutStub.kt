package workers.stub

import context.CorStatus
import context.MpContext
import handlers.CorChainDsl
import handlers.chain
import handlers.worker
import model.MpStubCases

internal fun CorChainDsl<MpContext>.workoutStub(title: String) {
	chain {
		this.title = title
		on { status == CorStatus.RUNNING && mpStubCases != MpStubCases.NONE }
		worker {
			this.title = "SUCCESS stubcase worker"
			on { mpStubCases == MpStubCases.SUCCESS }
			handle {
				responseWorkout = StubData.getModel()
				status = CorStatus.FINISHING
			}
		}
		worker {
			this.title = "Когда не подоходящего стаб кейса"
			on { status == CorStatus.RUNNING }
			handle {
				status = CorStatus.FAILING
				addError(
					e = Exception("No matching workerts for stubCase: ${mpStubCases}")
				)
			}
		}
	}
}