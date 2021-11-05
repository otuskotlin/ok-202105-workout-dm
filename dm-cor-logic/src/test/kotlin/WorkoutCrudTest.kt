import context.CorStatus
import context.MpContext
import kotlinx.coroutines.runBlocking
import model.MpStubCases
import model.WorkoutIdModel
import org.junit.Test
import kotlin.test.assertEquals

class WorkoutCrudTest {

	@Test
	fun workoutCreateSuccess() {
		val crud = WorkoutCrud()
		val context = MpContext(
			idRequest = "01",
			requestWorkout = StubData.getModel { id = WorkoutIdModel.NONE },
			operation = MpContext.MpOperations.CREATE,
			mpStubCases = MpStubCases.SUCCESS
		)
		runBlocking {
			crud.create(context)
		}

		val expected = StubData.getModel()
		assertEquals(CorStatus.SUCCESS, context.status)
		with(context.responseWorkout) {
			assertEquals(expected.id, id)
			assertEquals(expected.description, description)
			assertEquals(expected.items, items)
			assertEquals(expected.name, name)
			assertEquals(expected.ownerId, ownerId)
		}
	}
	@Test
	fun workoutCreateBeadValidation() {
		val crud = WorkoutCrud()
		val context = MpContext(
			idRequest = "", //Поле пустое
			requestWorkout = StubData.getModel { id = WorkoutIdModel.NONE },
			operation = MpContext.MpOperations.CREATE,
			mpStubCases = MpStubCases.SUCCESS
		)
		runBlocking {
			crud.create(context)
		}

		val expected = StubData.getModel()
		assertEquals(CorStatus.FAILING, context.status)
	}


	@Test
	fun workoutReadSuccess() {
		val crud = WorkoutCrud()

		val context = MpContext(
			requestWorkout = StubData.getModel(),
			operation = MpContext.MpOperations.CREATE,
			mpStubCases = MpStubCases.SUCCESS
		)
		runBlocking {
			crud.read(context)
		}
		assertEquals(CorStatus.SUCCESS, context.status)
		val expected = StubData.getModel()

		with(context.responseWorkout) {
			assertEquals(expected.id, id)
			assertEquals(expected.name, name)
			assertEquals(expected.description, description)
			assertEquals(expected.ownerId, ownerId)
			assertEquals(expected.items, items)
		}
	}

}