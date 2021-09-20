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
package ModelForRequest


import kotlinx.serialization.*

/**
 * 
 * @param mode 
 */
@Serializable
data class Debug (
    @SerialName(value = "mode") val mode: Debug.Mode? = null
) {

    /**
     * 
     * Values: PROD,TEST,STUB
     */
    @Serializable
    enum class Mode(val value: kotlin.String) {
        @SerialName(value = "prod") PROD("prod"),
        @SerialName(value = "test") TEST("test"),
        @SerialName(value = "stub") STUB("stub");
    }
}

