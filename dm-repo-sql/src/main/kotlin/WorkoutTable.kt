import org.jetbrains.exposed.sql.Table

object WorkoutTable : Table("WorkoutTable") {
	val id = uuid("id").autoGenerate().uniqueIndex()

	//	val ownerId :
	val name = varchar("name", 128)
	val description = varchar("name", 1024)
	val items = reference("items", )

}