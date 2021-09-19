package context

import InnerModel.CommonErrorModel
import InnerModel.IError
import InnerModel.IUserSession
import InnerModel.PaginatedModel
import InnerModel.WorkoutIdModel
import InnerModel.WorkoutModel


data class MpContext(
    val userSession: IUserSession<*> = IUserSession.Companion.EmptySession,

    var onRequest: String = "",
    var requestWorkoutId: WorkoutIdModel = WorkoutIdModel.NONE,
    var requestAd: WorkoutModel = WorkoutModel(),
    var responseAd: WorkoutModel = WorkoutModel(),
    var requestPage: PaginatedModel = PaginatedModel(),
    var responsePage: PaginatedModel = PaginatedModel(),
    var responseAds: MutableList<WorkoutModel> = mutableListOf(),
    val errors: MutableList<IError> = mutableListOf(),
    var status: CorStatus = CorStatus.NONE,
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