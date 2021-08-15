rootProject.name = "Main"

pluginManagement{
    val kotlinVersion : String by settings
    val openApiVersion: String by settings
    val serializationVersion: String by settings

    plugins{
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version serializationVersion
        id("org.openapi.generator") version openApiVersion
    }
}

include("TDD")
include("m2m2-testing")
include("SportProject")
include("DM_SportProject-transport-main-mp")
