package MyModels


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * упражнение его повторения, вес и подходы
 * @param nameExercise 
 * @param retry количество повторов
 * @param numberRepetitions количество повторений
 * @param ownWeight упражнение с своим весом?
 * @param weight вес в упражнении
 */
@Serializable
data class ExerciseTransfer (
    @SerialName(value = "nameExercise") val nameExercise: kotlin.String? = null,
    /* количество повторов */
    @SerialName(value = "retry") val retry: kotlin.Int? = null,
    /* количество повторений */
    @SerialName(value = "numberRepetitions") val numberRepetitions: kotlin.Int? = null,
    /* упражнение с своим весом? */
    @SerialName(value = "OwnWeight") val ownWeight: kotlin.Boolean? = null,
    /* вес в упражнении */
    @SerialName(value = "weight") val weight: kotlin.Int? = null
)

