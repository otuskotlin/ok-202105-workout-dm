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
package MyModels

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Структура для ответа на запрос с информацией об объявлении
 * @param messageType 
 * @param result 
 * @param errors 
 * @param readWorkout 
 */
@Serializable
data class ReadWorkoutResponse (
    @SerialName(value = "messageType") override val messageType: kotlin.String? = null,
    @SerialName(value = "result") override val result: BaseResponse.Result? = null,
    @SerialName(value = "errors") override val errors: kotlin.collections.List<ApiError>? = null,
    @SerialName(value = "readWorkout") val readWorkout: ResponseWorkout? = null
) : BaseMessage, BaseResponse

