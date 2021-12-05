package table

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("Users") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val name = varchar("name", 128)
    val surname = varchar("surname", 128)
    val description = varchar("description", 128)

    override val primaryKey = PrimaryKey(id)
}