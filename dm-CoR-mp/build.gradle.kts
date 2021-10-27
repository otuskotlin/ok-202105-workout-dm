plugins {
    kotlin("multiplatform")
}

group = "ru.ru.otus.kotlin"
version = "0.0.1"

kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    jvm{}

    sourceSets {
        val coroutinesVersion: String by project
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                implementation(project(":dm-transport-main-mp"))
                implementation(project(":dm-transport-inner-model-mp"))
                implementation(project(":dm-transport-mapping-mp"))
                implementation(project(":dm-stub-data"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}