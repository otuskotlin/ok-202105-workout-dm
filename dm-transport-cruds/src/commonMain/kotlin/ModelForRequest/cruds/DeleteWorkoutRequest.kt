/**
* SpotrProjcetAPI
* SpotrProjcetAPI
*
* The version of the OpenAPI document: 0.0.1
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package ModelForRequest.cruds

import ModelForRequest.Debug
import kotlinx.serialization.*

/**
 * Структура для запроса удаления тренеровки
 * @param messageType 
 * @param requestId 
 * @param debug 
 * @param deleteAdId 
 */
@Serializable
data class DeleteWorkoutRequest (
    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "requestId") override val requestId: kotlin.String? = null,
    @SerialName(value = "debug") override val debug: Debug? = null,
    @SerialName(value = "deleteAdId") val deleteAdId: kotlin.String? = null
) : BaseMessage, BaseRequest
