package table

import model.ExerciseModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object ExercisesTable : Table("exercises") {

	fun from(row: Iterable<ResultRow>): MutableList<ExerciseModel> {
		return row.map {
			from(it)
		}.toMutableList()
	}

	fun from(row: ResultRow): ExerciseModel{
		return ExerciseModel(
			nameExercise = row[nameExercise],
			retry = row[retry],
			numberRepetitions = row[numberRepetitions],
			ownWeight = row[ownWeight],
			weight = row[weight]
		)
	}

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