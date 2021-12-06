package validators.workers

import ICorChainDsl
import RepoWorkoutSql
import SqlConnector
import context.MpContext
import handlers.worker

internal fun ICorChainDsl<MpContext>.choseBd(title: String) = worker {

	this.title = title
	description = "Data from request are stored in the DB Repository"

	on { status == context.CorStatus.RUNNING }

	handle {
		iRepoWorkout = RepoWorkoutSql()
	}
}