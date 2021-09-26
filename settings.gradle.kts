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

include("SportProject")
include("dm-transport-main-mp")
include("dm-transport-mp-Context-inModel")
include("dm-transport-mapping-mp")
include("dm-ktor-code-version")
include("dm-stub-data")
