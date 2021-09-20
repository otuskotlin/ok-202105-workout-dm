package ModelForRequest.cruds

import ModelForRequest.ApiError
import ModelForRequest.ResponseWorkout
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateWorkoutResponse(
    @SerialName(value = "messageType") override val messageType: String? = null,
    @SerialName(value = "createdWorkout") val createdWorkout: ResponseWorkout? = null,
    override val result: BaseResponse.Result? = BaseResponse.Result.SUCCESS,
    override val errors: List<ApiError>?,
    override val requestId: String?,

    ) : BaseMessage, BaseResponse {

}