package MyModels.cruds

import MyModels.Debug
import MyModels.UpdateWorkout
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Структура для запроса обновления существующей тренеровки
 * @param messageType 
 * @param requestId 
 * @param debug 
 * @param createAd 
 */
@Serializable
data class UpdateWorkoutRequest (
    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "requestId") override val requestId: kotlin.String? = null,
    @SerialName(value = "debug") override val debug: Debug? = null,
    @SerialName(value = "createAd") val createAd: UpdateWorkout? = null
) : BaseMessage, BaseRequest
