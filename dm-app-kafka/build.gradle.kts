plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.5.20"
}

application{
    mainClass.set("Mainkt")
}

dependencies {
    val kafkaVersion: String by project
    val coroutinesVersion: String by project

    implementation(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    implementation(project(":dm-service"))
    implementation(project(":dm-transport-inner-model-mp"))
    implementation(project(":dm-transport-main-mp"))
    implementation(project(":dm-cor-logic"))
    implementation(project(":dm-transport-main-mp"))

    testImplementation(kotlin("test-junit"))
}