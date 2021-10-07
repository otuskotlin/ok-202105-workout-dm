import kotlin.test.Test

class CorTest {
	@Test
	fun corTest() {
		val chain = chain<TestContext> {
			worker {
				title = "Some operation"
				description = "Description of an operation"

				on { some > 0 }
				handle { some += 10 }
				except { e: Throwable -> println("Exception!: ${e.message}") }
			}

			chain {
				on { some > 0 }


				worker(
					title = "other worker",
					description = "another wat of using worker"

				) {
					some++
				}

			}

			parallel {
				on { some > 0 }

				worker {
					handle { some += 10 }
				}
			}

			printResult(title = "Вывод результата")


		}.build()

	}
}

fun <T> chain(function: CorChainDsl<T>.() -> Unit): CorChainDsl = CorChainDsl().apply(function)


class CorChainDsl<T> {

	fun build() = CorChain()


}

class CorChain<T>(
	val title: String,
	val description: String,
	val blockOn: T.() -> Boolean,
	val blockHandle: T.() -> Unit,
	val blocExcept: T.()
)
