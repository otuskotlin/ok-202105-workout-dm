package validators.workers

import ICorChainDsl
import context.CorStatus
import context.MpContext
import handlers.worker
import repository.DbWorkoutFilterRequest
import repository.DbWorkoutIdRequest
import repository.DbWorkoutModelRequest

internal fun ICorChainDsl<MpContext>.repoCreate(title: String) = worker {
	this.title = title
	description = "Create request to BD"

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

internal fun ICorChainDsl<MpContext>.repoRead(title: String) = worker {
	this.title = title
	description = "Read request to BD"

	on { status == CorStatus.RUNNING }

	handle {
		val result = iRepoWorkout.read(DbWorkoutIdRequest(requestWorkoutId))
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

internal fun ICorChainDsl<MpContext>.repoUpdate(title: String) = worker {
	this.title = title
	description = "Update request to BD"

	on { status == CorStatus.RUNNING }

	handle {
		val result = iRepoWorkout.update(DbWorkoutModelRequest(requestWorkout))
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

internal fun ICorChainDsl<MpContext>.repoDelete(title: String) = worker {
	this.title = title
	description = "Update request to BD"

	on { status == CorStatus.RUNNING }

	handle {
		val result = iRepoWorkout.delete(DbWorkoutIdRequest(requestWorkoutId))
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

internal fun ICorChainDsl<MpContext>.repoSearch(title: String) = worker {
	this.title = title
	description = "Update request to BD"

	on { status == CorStatus.RUNNING }

	handle {
		val result = iRepoWorkout.search(DbWorkoutFilterRequest("NotUseThisString", ownerIdModel))
		val resultValue = result.result
		if (result.isSuccess && resultValue != null) {
			responseWorkouts = resultValue
		} else {
			addError(
				e = Exception("request failed ${description}")
			)

		}
	}
}
