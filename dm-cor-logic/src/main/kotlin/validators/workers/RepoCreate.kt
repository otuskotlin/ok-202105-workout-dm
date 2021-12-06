package validators.workers

import ICorChainDsl
import context.CorStatus
import context.MpContext
import handlers.worker
import repository.DbWorkoutModelRequest

internal fun ICorChainDsl<MpContext>.repoCreate(title: String) = worker {
	this.title = title
	description = "request to BD"

	on { status == CorStatus.RUNNING }

	handle {
		val result = iRepoWorkout.create(DbWorkoutModelRequest(requestWorkout))
		val resultValue = result.result
		if (result.isSuccess && resultValue != null) {
			responseWorkout = resultValue
		} else {
			addError(
				e = Exception("request failed ${description}")
			)

		}
	}
}