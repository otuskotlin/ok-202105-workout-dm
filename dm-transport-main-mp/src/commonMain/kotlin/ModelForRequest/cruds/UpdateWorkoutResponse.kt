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


import ModelForRequest.ApiError
import ModelForRequest.ResponseWorkout
import kotlinx.serialization.*

/**
 * Структура для ответа на запрос с информацией об тренеровке
 * @param messageType 
 * @param result 
 * @param errors 
 * @param updatedAd 
 */
@Serializable
data class UpdateWorkoutResponse (
    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "result") override val result: BaseResponse.Result? = null,
    @SerialName(value = "errors") override val errors: kotlin.collections.List<ApiError>? = null,
    @SerialName(value = "updatedAd") val updatedAd: ResponseWorkout? = null
) : BaseMessage, BaseResponse{

}