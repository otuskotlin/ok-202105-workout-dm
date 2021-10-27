import chains.WorkoutCreate
import context.MpContext

class WorkoutCrud {

	suspend fun create(cts: MpContext) {
		WorkoutCreate.exec(cts)
	}

	suspend fun read(cts: MpContext) {}
	suspend fun update(cts: MpContext) {}
	suspend fun delete(cts: MpContext) {}
	suspend fun search(cts: MpContext) {}

}