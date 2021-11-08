package context

import model.CommonErrorModel
import model.IError
import model.IUserSession
import model.MpStubCases
import model.PaginatedModel
import model.WorkoutIdModel
import model.WorkoutModel


data class MpContext(
	val userSession: IUserSession<*> = IUserSession.Companion.EmptySession,

	var idRequest: String = "",
	var requestWorkoutId: WorkoutIdModel = WorkoutIdModel.NONE,
	var requestWorkout: WorkoutModel = WorkoutModel(),
	var responseWorkout: WorkoutModel = WorkoutModel(),
	var requestPage: PaginatedModel = PaginatedModel(),
	var responsePage: PaginatedModel = PaginatedModel(),
	var responseWorkouts: MutableList<WorkoutModel> = mutableListOf(),
	val errors: MutableList<IError> = mutableListOf(),
	var status: CorStatus = CorStatus.NONE,

	var operation: MpOperations = MpOperations.NONE,
	var mpStubCases: MpStubCases = MpStubCases.NONE,

	) {
	enum class MpOperations {
		NONE,
		INIT,
		CREATE,
		READ,
		UPDATE,
		DELETE,
		SEARCH,
		OFFER
	}

	/**
	 * Добавляет ошибку в контекст
	 *
	 * @param error Ошибка, которую необходимо добавить в контекст
	 * @param failingStatus Необходимо ли установить статус выполнения в FAILING (true/false)
	 */
	suspend fun addError(error: IError, failingStatus: Boolean = true) = apply {
		if (failingStatus) status = CorStatus.FAILING
		errors.add(error)
	}


	suspend fun addError(
		e: Throwable,
		level: IError.Level = IError.Level.ERROR,
		field: String = "",
		failingStatus: Boolean = true
	) {
		addError(CommonErrorModel(e, field = field, level = level), failingStatus)
	}
}
