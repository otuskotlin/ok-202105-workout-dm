plugins {
    application
    kotlin("jvm")
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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")


    implementation(project(":dm-service"))
    implementation(project(":dm-transport-inner-model-mp"))
    implementation(project(":dm-cor-logic"))
    implementation(project(":dm-transport-main-mp"))

    testImplementation(kotlin("test-junit"))
}