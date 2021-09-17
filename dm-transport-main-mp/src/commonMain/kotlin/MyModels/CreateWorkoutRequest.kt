package MyModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ru.otus.kotlin.kmp.transport.models.CreateWorkout
import ru.ru.otus.kotlin.kmp.transport.models.WorkoutAddRequestAllOfDebug

@Serializable
data class CreateWorkoutRequest (

    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "requestId") val requestId: kotlin.String? = null,
    @SerialName(value = "debug") val debug: WorkoutAddRequestAllOfDebug? = null,
    @SerialName(value = "createWorkout") val createWorkout: CreateWorkout? = null

) : BaseMessage