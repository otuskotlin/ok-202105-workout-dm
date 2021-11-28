import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.DEFAULT_ISOLATION_LEVEL
import org.jetbrains.exposed.sql.transactions.transaction

class SqlConnector(
	private val url: String = "jdbc://postgresql://localhost:5432/SportProject",
	private val user: String = "postgres",
	private val password: String = "postgres",
	private val schema: String = "default_schema",
	private val databaseConfig: DatabaseConfig = DatabaseConfig { defaultIsolationLevel = DEFAULT_ISOLATION_LEVEL }
) {
	private enum class DbType(val driver: String) {
		MYSQL("com.mysql.cj.jdbc.Driver"),
		POSTGRESQL("org.postgresql.Driver")
	}

	private val dbType: DbType = url.let { url ->
		when {
			url.startsWith("jdbc:mysql://") -> DbType.MYSQL
			url.startsWith("jdbc:postgresql://") -> DbType.POSTGRESQL
			else -> error("Cannot parse database type from url: $url. `jdbc:mysql://...` and `jdbc:postgresql://` are supported only.")
		}
	}

	val db = Database.connect(url, dbType.driver, user, password)

	private val globalConnection = Database.connect(url, dbType.driver, user, password, databaseConfig = databaseConfig)

	fun connection(vararg tables: Table): Database {

		transaction(globalConnection) {
			when (dbType) {
				DbType.MYSQL -> {
					val dbSettings = " DEFAULT CHARACTER SET utf8mb4\n" +
							" DEFAULT COLLATE utf8mb4_general_ci"
					connection.prepareStatement("CREATE DATABASE IF NOT EXISTS $schema\n$dbSettings", false)
						.executeUpdate()
					connection.prepareStatement("ALTER DATABASE $schema\n$dbSettings", false).executeUpdate()
				}
				DbType.POSTGRESQL -> {
					connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS $schema", false).executeUpdate()
				}
			}
		}

//		return Database.connect();
	}
}