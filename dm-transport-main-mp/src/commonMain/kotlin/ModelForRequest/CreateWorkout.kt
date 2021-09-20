package ModelForRequest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWorkout (
    /* id */
    @SerialName(value = "id") override val id: String? = null,
    override val ownerId : String? = null,
    /* Название упражнения */
    @SerialName(value = "name") val name: kotlin.String? = null,
    /* Описание упражнения */
    @SerialName(value = "description") val description: kotlin.String? = null,
    @SerialName(value = "items") val items: List<ExerciseTransfer>? = null
) : BaseWorkout