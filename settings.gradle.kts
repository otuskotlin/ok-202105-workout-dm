rootProject.name = "Main"

pluginManagement{
	val kotlinVersion: String by settings
	val openApiVersion: String by settings
	val serializationVersion: String by settings

	plugins {
		kotlin("jvm") version kotlinVersion
		kotlin("multiplatform") version kotlinVersion
		kotlin("plugin.serialization") version kotlinVersion
		id("org.openapi.generator") version openApiVersion
	}
}

include("dm-transport-main-mp")
include("dm-transport-inner-model-mp")
include("dm-transport-mapping-mp")
include("dm-app-sport-project-ktor")
include("dm-stub-data")
include("dm-stub-data")
include("dm-CoR-mp")
include("dm-cor-logic")
