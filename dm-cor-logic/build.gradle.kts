plugins {
	kotlin("jvm")
}

dependencies {

    val coroutinesVersion: String by project

	implementation(kotlin("stdlib"))

	implementation(project(":dm-CoR-mp"))
	implementation(project(":dm-stub-data"))
	implementation(project(":dm-transport-inner-model-mp"))
	implementation(project(":dm-CoR-validation"))
	implementation(project(":dm-repo-sql"))

    testImplementation(kotlin("test"))
	testImplementation(kotlin("test-junit"))
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

}