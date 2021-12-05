package table

import model.OwnerIdModel
import model.WorkoutIdModel
import model.WorkoutModel
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.*

object WorkoutTable : Table("Workouts") {
	val id = uuid("id").autoGenerate().uniqueIndex()

	val ownerId = reference("owner_id", UsersTable.id)
	val name = varchar("name", 128)
	val description = varchar("description", 1024)

	override val primaryKey = PrimaryKey(ExercisesTable.id)


	fun from(res: InsertStatement<Number>) = WorkoutModel(
		id = WorkoutIdModel(res[id]),
		ownerId = OwnerIdModel(res[ownerId]),
		name = res[name],
		description = res[description],
		excersices = mutableListOf()
	)

//		fun from(res: InsertStatement<Number>) = AdModel(
//			id = AdIdModel(res[id]),
//			title = res[title],
//			description = res[description],
//			ownerId = OwnerIdModel(res[ownerId]),
//			visibility = res[visibility],
//			dealSide = res[dealSide]
//		)

}