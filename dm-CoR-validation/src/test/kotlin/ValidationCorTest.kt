import context.MpContext
import cor.validation
import kotlinx.coroutines.runBlocking
import lib.ValidationResult
import lib.exception.ValidationError
import lib.validators.StringNotEmptyValidator
import org.junit.Test
import kotlin.test.assertEquals

class ValidationCorTest {

	@Test
	fun `test validation in cor`() {
		val context = MpContext()

		val chain = chain<TestDataContext> {
			validation {

				errorhandler { result: ValidationResult ->
					if (!result.isSuccess) {
						this.errors.addAll(result.errors)
					}

				}
				validate {
					validator(StringNotEmptyValidator() on { x })
				}
				validate {
					validator(StringNotEmptyValidator() on { y })
				}
			}
		}
		runBlocking {
			val context = TestDataContext()

			assertEquals(context.errors.size, 0)
			chain.build().exec(context)
			assertEquals(context.errors.size, 2)
		}

	}

	data class TestDataContext(
		val x: String = "",
		val y: String = "",

		val errors: List<ValidationError> = listOf()
	)
}