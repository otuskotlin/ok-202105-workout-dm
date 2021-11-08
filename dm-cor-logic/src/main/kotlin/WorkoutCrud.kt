import chains.WorkoutCreate
import chains.WorkoutDelete
import chains.WorkoutRead
import chains.WorkoutSearch
import chains.WorkoutUpdate
import context.MpContext

class WorkoutCrud {

	suspend fun create(cts: MpContext) {
		WorkoutCreate.exec(cts)
	}

	suspend fun read(cts: MpContext) {
		WorkoutRead.exec(cts)
	}

	suspend fun update(cts: MpContext) {
		WorkoutUpdate.exec(cts)
	}

	suspend fun delete(cts: MpContext) {
		WorkoutDelete.exec(cts)
	}

	suspend fun search(cts: MpContext) {
		WorkoutSearch.exec(cts)
	}
}