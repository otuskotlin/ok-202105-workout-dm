package ModelForRequest.cruds

import kotlinx.serialization.SerialName

sealed interface BaseMessage{
    @SerialName(value = "messageType")
    val messageType: kotlin.String?
}