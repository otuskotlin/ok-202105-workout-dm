package table

import org.jetbrains.exposed.sql.Table

object ExercisesTable : Table("Exercises") {
    val id = uuid("id").autoGenerate().uniqueIndex()

    var nameExercise = varchar("name", 128)

    /* количество повторов */
    var retry = integer("retry")

    /* количество повторений */
    var numberRepetitions = integer("number_repetitions")

    /* упражнение с своим весом? */
    var ownWeight = bool("own_weight")

    /* вес в упражнении */
    var weight = integer("weight")

}