package table

import org.jetbrains.exposed.sql.Table

object WorkoutTable : Table("Workouts") {
	val id = uuid("id").autoGenerate().uniqueIndex()

	val ownerId = reference("owner_id", UsersTable.id)
	val name = varchar("name", 128)
	val description = varchar("name", 1024)
	val exercises = reference("items", ExercisesTable.id)

	override val primaryKey = PrimaryKey(ExercisesTable.id)
}