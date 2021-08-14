rootProject.name = "Main"

pluginManagement{
    val kotlinVersion : String by settings

    plugins{
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
    }
}

include("TDD")
include("m2m2-testing")
include("SportProject")
