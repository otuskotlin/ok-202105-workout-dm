package ModelForRequest.cruds

import ModelForRequest.Debug
import ModelForRequest.UpdateWorkout
import kotlinx.serialization.*

/**
 * Структура для запроса обновления существующей тренеровки
 * @param messageType 
 * @param requestId 
 * @param debug 
 * @param updateWorkout
 */
@Serializable
data class UpdateWorkoutRequest (
    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "requestId") override val requestId: kotlin.String? = null,
    @SerialName(value = "debug") override val debug: Debug? = null,
    @SerialName(value = "updateWorkout") val updateWorkout: UpdateWorkout? = null
) : BaseMessage, BaseRequest
