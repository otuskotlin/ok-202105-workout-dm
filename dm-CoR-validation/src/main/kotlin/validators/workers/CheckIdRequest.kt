package validators.workers

import context.MpContext
import cor.ValidationBuilder
import validators.ValidatorStringNonEmpty

public fun ValidationBuilder<MpContext>.checkIdRequest() {
	validate<String?> {
		on { this.idRequest }
		validator(ValidatorStringNonEmpty())
	}
}