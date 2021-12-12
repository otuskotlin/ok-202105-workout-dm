plugins {
    kotlin("jvm")
}

group = "ru.ru.otus.kotlin"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":dm-transport-cruds"))
    implementation(project(":dm-transport-inner-model-mp"))
    implementation(project(":dm-transport-mapping-mp"))

    implementation(kotlin("test-common"))
    implementation(kotlin("test-annotations-common"))
}