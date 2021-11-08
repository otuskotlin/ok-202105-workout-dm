plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":dm-cor-logic"))
    implementation(project(":dm-stub-data"))
    implementation(project(":dm-transport-mapping-mp"))
    implementation(project(":dm-transport-inner-model-mp"))
    implementation(project(":dm-transport-main-mp"))
}