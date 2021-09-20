package ModelForRequest.cruds

import kotlinx.serialization.SerialName

interface BaseMessage{
    @SerialName(value = "messageType")
    val messageType: kotlin.String?
}