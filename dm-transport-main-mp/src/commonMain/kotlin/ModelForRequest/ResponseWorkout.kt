package ModelForRequest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ru.otus.kotlin.kmp.transport.models.WorkoutPermissions

@Serializable
data class ResponseWorkout (
    @SerialName(value = "id") override val id: kotlin.String? = null,
    /* Название упражнения */
    @SerialName(value = "name") val name: kotlin.String? = null,
    /* Описание упражнения */
    @SerialName(value = "description") val description: kotlin.String? = null,
    @SerialName(value = "items") val items: kotlin.collections.List<ExerciseTransfer>? = null,
    @SerialName(value = "permissions") val permissions: kotlin.collections.List<WorkoutPermissions>? = null,
    override val ownerId: String?
): BaseWorkout