import context.CorStatus
import context.MpContext
import org.junit.Test
import kotlin.test.assertEquals

class WorkoutCrudTest {

	@Test
	fun workoutCreateSuccess(){
		val crud = WorkoutCrud()
		val context = MpContext(
			requestWorkout = StubData.getModel(),
			operation = MpContext.MpOperations.CREATE,
			stubCase = MpStubCases.SUCCESS
		)

		crud.create(context)
		val expected = StubData.getModel()
		assertEquals(CorStatus.SUCCESS, context.status)
		with(context.responseWorkout){
			assertEquals(expected.id, id)
//			assertEquals(expected)
		}

	}



}