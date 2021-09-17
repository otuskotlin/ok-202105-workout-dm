package MyModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status (
    val stat : Stat = Stat.SUCCESS
){
    enum class Stat(val value: kotlin.String) {
        @SerialName(value = "success")
        SUCCESS("success"),
        @SerialName(value = "error")
        ERROR("error"),
    }
}
