package MyModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWorkout (
    /* id */
    @SerialName(value = "id") val id: kotlin.String? = null,
    /* Название упражнения */
    @SerialName(value = "name") val name: kotlin.String? = null,
    /* Описание упражнения */
    @SerialName(value = "description") val description: kotlin.String? = null,
    @SerialName(value = "items") val items: kotlin.collections.List<ExerciseTransfer>? = null
)