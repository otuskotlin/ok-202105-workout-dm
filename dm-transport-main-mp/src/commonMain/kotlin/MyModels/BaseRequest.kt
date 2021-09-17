package MyModels

import kotlinx.serialization.SerialName

sealed interface BaseRequest{
    @SerialName(value = "requestId") val requestId: kotlin.String?
    @SerialName(value = "debug") val debug: Debug?
}