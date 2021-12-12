val ktorVersion: String by project
val logbackVersion: String by project
val serializationVersionCore: String by project

plugins {
	application
	kotlin("jvm")
}

application {
	mainClass.set("com.example.ApplicationKt")
}

fun DependencyHandler.ktor(module: String, version: String = ktorVersion): String{
	return "io.ktor:ktor-$module:$version"
}

dependencies {

	implementation(project(":dm-transport-cruds"))
	implementation(project(":dm-transport-inner-model-mp"))
	implementation(project(":dm-transport-mapping-mp"))
	implementation(project(":dm-stub-data"))
	implementation(project(":dm-service"))
	implementation(project(":dm-cor-logic"))

	implementation(ktor("server-core"))
	implementation(ktor("server-tomcat"))
	implementation(ktor("serialization"))

//	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersionCore")


	implementation("ch.qos.logback:logback-classic:$logbackVersion")

	testImplementation(ktor("server-tests"))
	testImplementation(kotlin("test-junit"))

}