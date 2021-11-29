plugins {
    kotlin("jvm")
}

group = "ru.ru.otus.kotlin"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    val exposedVersion: String by project
    val postgresDriverVersion: String by project
//    val testContainersVersion: String by project

    implementation(kotlin("stdlib"))

    implementation(project(":dm-transport-inner-model-mp"))

    implementation("org.postgresql:postgresql:$postgresDriverVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}