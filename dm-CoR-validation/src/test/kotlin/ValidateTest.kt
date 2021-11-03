import lib.validators.StringNotEmptyValidator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIsNot
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ValidateTest {

	@Test
	fun `String validation ok`() {
		val data = "not Empty"

		val validator = StringNotEmptyValidator()
		val res = validator.validate(data)


		assertTrue(res.isSuccess)
		assertNull(res.errors.find { it.message.contains("empty") })
	}

//	@Test
//	fun `String validation error`() {
//		val data = ""
//
//		val validator = StringNotEmptyValidator()
//		val res = validator.validate(data)
//
//
//		assertEquals(res, SUCCESS)
//		assertTrue(res.errors.find { it.message.contains("empty") })
//	}

}