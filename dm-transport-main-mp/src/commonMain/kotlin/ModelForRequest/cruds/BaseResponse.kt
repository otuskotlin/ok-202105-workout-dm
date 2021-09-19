package ModelForRequest.cruds;

import ModelForRequest.ApiError
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface BaseResponse {
    @SerialName(value = "result")
    val result: Result?
    @SerialName(value = "errors")
    val errors: kotlin.collections.List<ApiError>?

    @Serializable
    enum class Result(val value: kotlin.String) {
        @SerialName(value = "success")
        SUCCESS("success"),
        @SerialName(value = "error")
        ERROR("error");
    }
}
