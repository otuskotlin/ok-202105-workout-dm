plugins {
    application
    kotlin("jvm")
}

application{
    mainClass.set("Mainkt")
}

dependencies {
    val kafkaVersion: String by project

    implementation(kotlin("stdlib"))
    // https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
    implementation(project(":dm-service"))
    implementation(project(":dm-cor-logic"))

    testImplementation(kotlin("test-junit"))
}