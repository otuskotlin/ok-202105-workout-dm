import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals

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

		val ctx = TestContext(10)
		runBlocking {
			chain.exec(ctx)
		}
		assertEquals(26, ctx.some)

	}
}

data class TestContext(
	var some: Int = 0
)

fun <T> chain(function: CorChainDsl<T>.() -> Unit): CorChainDsl<T> = CorChainDsl<T>().apply(function)
fun <T> ICorChainDsl<T>.chain(function: CorChainDsl<T>.() -> Unit): CorChainDsl<T> = CorChainDsl<T>().apply(function)
fun <T> ICorChainDsl<T>.parallel(function: CorParallelDsl<T>.() -> Unit): CorChainDsl<T> =
	CorChainDsl<T>().apply(function)
//todo parallel ебаную чушь скопипастить, или посмотрель как это сделали по людски


class CorChainDsl<T>(
	private val workers: MutableList<ICorExecDsl<T>> = mutableListOf(),
	private var blockOn: T.() -> Boolean = { true },
	private var blocExcept: T.(Throwable) -> Unit = {},
	override var title: String,
	override var description: String
) : ICorChainDsl<T> {

	override fun except(function: T.(Throwable) -> Unit) {
		blocExcept = function
	}

	override fun on(function: T.() -> Boolean) {
		blockOn = function
	}

	override fun build() = CorChain<T>(
		title = title,
		description = description,
		blockOn = blockOn,
		blocExcept = blocExcept,
		workers = workers.map { it.build() },

	)

	override fun add(worker: ICorExecDsl<T>) {
		workers.add(worker)
	}
}

fun <T> ICorChainDsl<T>.worker(function: ICorExecDsl<T>.() -> Unit) = CorWorkerDsl<T>()

interface ICorChainDsl<T> : ICorExecDsl<T> {
	fun on(function: T.() -> Boolean)
	fun except(function: T.(Throwable) -> Unit)
	fun add(worker: ICorExecDsl<T>)
}

interface ICorExecDsl<T> {
	val title: String
	val description: String
	fun build(): ICorExec<T>
}

interface ICorExec<T> {
	suspend fun exec(ctx: T)
}

interface ICorWorker<T> : ICorExec<T> {

	override suspend fun exec(ctx: T) {
		try {
			if (on(ctx)) handle(ctx)
		} catch (e: Throwable) {
			except(ctx, e)
		}
	}

	fun on(ctx: T): Boolean
	suspend fun handle(ctx: T)
	fun except(ctx: T, e: Throwable)

}

class CorChain<T>(
	val title: String,
	val description: String,
	val blockOn: T.() -> Boolean = { true },
	val blocExcept: T.(Throwable) -> Unit = { e: Throwable -> throw e },
	val workers: List<ICorExec<T>>
) : ICorChain<T> {
	override fun on(ctx: T): Boolean = blockOn(ctx)

	override fun except(ctx: T, e: Throwable) = blocExcept(ctx, e)
	override suspend fun handle(ctx: T) {
		workers.forEach {
			it.exec(ctx)
		}
	}
}

class CorParallel<T>(
	val title: String,
	val description: String,
	val blockOn: T.() -> Boolean = { true },
	val blocExcept: T.(Throwable) -> Unit = { e: Throwable -> throw e },
	val workers: List<ICorExec<T>>
) : ICorChain<T> {
	override fun on(ctx: T): Boolean = blockOn(ctx)

	override fun except(ctx: T, e: Throwable) = blocExcept(ctx, e)

	override suspend fun handle(ctx: T): Unit = coroutineScope {
		workers.map {
			launch { it.exec(ctx) }
		}
			.toList()
			.forEach { it.join() }
	}
}

interface ICorChain<T> : ICorWorker<T> {
}

interface ICorWorkerDsl<T> : ICorExecDsl<T> {
	fun on(function: T.() -> Boolean)
	fun handle(function: T.() -> Unit)
	fun except(function: T.(Throwable) -> Unit)
}


