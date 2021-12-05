package table

import org.jetbrains.exposed.sql.Table

object ExercisesTable : Table("exercises") {
    val id = uuid("id").autoGenerate().uniqueIndex()

    var nameExercise = varchar("name_exercise", 128)

    /* количество повторов */
    var retry = integer("retry")

    /* количество повторений */
    var numberRepetitions = integer("number_repetition")

    /* упражнение с своим весом? */
    var ownWeight = bool("own_weight")

    /* вес в упражнении */
    var weight = integer("weight")

    val workout_id = reference("workout_id", UsersTable.id)

    override val primaryKey = PrimaryKey(id)
}