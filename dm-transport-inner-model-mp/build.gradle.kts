plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.5.20"
}

group = "ru.ru.otus.kotlin"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
//    val testContainersVersion: String by project
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    implementation(project(":dm-transport-cruds"))
}

