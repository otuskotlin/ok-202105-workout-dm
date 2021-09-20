package ModelForRequest

import kotlinx.serialization.*

/**
 * 
 * @param id 
 * @param name Название упражнения
 * @param description Описание упражнения
 * @param items 
 */
@Serializable
data class UpdateWorkout (
    @SerialName(value = "id") override val id: String? = null,
    /* Название упражнения */
    @SerialName(value = "name") val name: String? = null,
    /* Описание упражнения */
    @SerialName(value = "description") val description: kotlin.String? = null,
    @SerialName(value = "items") val items: kotlin.collections.List<ExerciseTransfer>? = null,
    override val ownerId: String?
) : BaseWorkout