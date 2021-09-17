package MyModels.cruds

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseMessage{
    @SerialName(value = "messageType")
    val messageType: kotlin.String?
}