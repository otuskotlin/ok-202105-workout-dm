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
 * Структура для ответа на запрос об удалении тренеровки
 * @param messageType 
 * @param result 
 * @param errors 
 * @param deletedAd 
 */
@Serializable
data class DeleteWorkoutResponse (
	@SerialName(value = "messageType") override val messageType: kotlin.String? = null,
	@SerialName(value = "result") override val result: ModelForRequest.cruds.BaseResponse.Result? = null,
	@SerialName(value = "errors") override val errors: kotlin.collections.List<ApiError>? = null,
	@SerialName(value = "deletedAd") val deletedAd: ResponseWorkout? = null
) : BaseMessage, ModelForRequest.cruds.BaseResponse
