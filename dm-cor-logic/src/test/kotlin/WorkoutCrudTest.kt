import context.CorStatus
import context.MpContext
import kotlinx.coroutines.runBlocking
import model.MpStubCases
import org.junit.Test
import kotlin.test.assertEquals

class WorkoutCrudTest {

	@Test
	fun workoutCreateSuccess(){
		val crud = WorkoutCrud()
		val context = MpContext(
			requestWorkout = StubData.getModel(),
			operation = MpContext.MpOperations.CREATE,
			mpStubCases = MpStubCases.SUCCESS
		)
		runBlocking {
			crud.create(context)
			val expected = StubData.getModel()
			assertEquals(CorStatus.SUCCESS, context.status)
			with(context.responseWorkout) {
				assertEquals(expected.id, id)
			}
		}

	}



}