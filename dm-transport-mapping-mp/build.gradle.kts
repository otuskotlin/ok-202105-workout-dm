plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":dm-transport-inner-model-mp"))
    implementation(project(":dm-transport-cruds"))
}