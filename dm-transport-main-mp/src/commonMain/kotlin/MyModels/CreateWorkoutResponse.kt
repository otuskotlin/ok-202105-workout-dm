package MyModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ru.otus.kotlin.kmp.transport.models.ApiError
import ru.ru.otus.kotlin.kmp.transport.models.ResponseWorkout

@Serializable
class CreateWorkoutResponse(
    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "createdWorkout") val createdWorkout: ResponseWorkout? = null,
    override val result: BaseResponse.Result? = BaseResponse.Result.SUCCESS,
    override val errors: List<ApiError>?,

    ) : BaseMessage ,BaseResponse {

}