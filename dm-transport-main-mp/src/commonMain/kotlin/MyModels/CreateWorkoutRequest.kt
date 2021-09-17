package MyModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ru.otus.kotlin.kmp.transport.models.CreateWorkout
import ru.ru.otus.kotlin.kmp.transport.models.Debug

@Serializable
data class CreateWorkoutRequest (

    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "requestId") override val requestId: kotlin.String? = null,
    @SerialName(value = "debug") override val debug: Debug? = null,
    @SerialName(value = "createWorkout") val createWorkout: CreateWorkout? = null,
    @SerialName(value = "status") val status : Status? = null

) : BaseMessage, BaseRequest