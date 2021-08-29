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
package ru.ru.otus.kotlin.kmp.transport.models

import kotlinx.serialization.*

/**
 * Пользователь
 * @param id 
 * @param name Имя пользователя
 * @param age Возраст
 * @param mass Вес
 * @param regData 
 */
@Serializable
data class User (
    @SerialName(value = "id") 
    var id: kotlin.String = "",
    /* Имя пользователя */
    @SerialName(value = "name") var name: kotlin.String = "",
    /* Возраст */
    @SerialName(value = "age") var age: kotlin.Int = 0,
    /* Вес */
    @SerialName(value = "mass") var mass: kotlin.Int = 0,
    @SerialName(value = "regData") var regData: RegData = RegData.EmpyRegData
){
    companion object{
        val EmptyUser = User()
    }
}
