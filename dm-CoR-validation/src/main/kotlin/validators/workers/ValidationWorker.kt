import cor.ValidationBuilder
import handlers.worker

fun <C> ICorChainDsl<C>.validation(block: ValidationBuilder<C>.() -> Unit) {
    worker {
        this.title = "Валидация"
        description = "Валидация логики"
//        on { status == CorStatus.RUNNING }
//        except { status = CorStatus.FAILING }
        handle {
            ValidationBuilder<C>().apply(block).build().exec(this)
        }
    }
}