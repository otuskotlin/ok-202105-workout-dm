package MyModels

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 * @param id 
 * @param name Название упражнения
 * @param description Описание упражнения
 * @param items 
 */
@Serializable
data class UpdateWorkout (
    @SerialName(value = "id") val id: kotlin.String? = null,
    /* Название упражнения */
    @SerialName(value = "name") val name: kotlin.String? = null,
    /* Описание упражнения */
    @SerialName(value = "description") val description: kotlin.String? = null,
    @SerialName(value = "items") val items: kotlin.collections.List<ExerciseTransfer>? = null
)