package MyModels

import kotlinx.serialization.SerialName
import ru.ru.otus.kotlin.kmp.transport.models.Debug

sealed interface BaseRequest{
    @SerialName(value = "requestId") val requestId: kotlin.String?
    @SerialName(value = "debug") val debug: Debug?
}