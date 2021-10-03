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

repositories {
	mavenCentral()
}

fun DependencyHandler.ktor(module: String, version: String = ktorVersion): String{
	return "io.ktor:ktor-$module:$version"
}

dependencies {

	implementation(project(":dm-transport-main-mp"))
	implementation(project(":dm-transport-mp-Context-inModel"))
	implementation(project(":dm-transport-mapping-mp"))

	implementation(ktor("server-core"))
	implementation(ktor("server-netty"))
	implementation(ktor("serialization"))

//	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersionCore")


	implementation("ch.qos.logback:logback-classic:$logbackVersion")

	testImplementation(ktor("server-tests"))
	testImplementation(kotlin("test-junit"))

}