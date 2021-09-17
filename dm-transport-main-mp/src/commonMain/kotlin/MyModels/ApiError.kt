package MyModels


import kotlinx.serialization.*

/**
 * 
 * @param message 
 * @param field 
 */
@Serializable
data class ApiError (
    @SerialName(value = "message") val message: kotlin.String? = null,
    @SerialName(value = "field") val field: kotlin.String? = null
)

