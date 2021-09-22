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

dependencies {
	implementation("io.ktor:ktor-server-core:$ktorVersion")
	implementation("io.ktor:ktor-server-netty:$ktorVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersionCore")

	implementation("ch.qos.logback:logback-classic:$logbackVersion")

	testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
	testImplementation(kotlin("test-junit"))
//	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}