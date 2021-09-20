plugins {
	kotlin("multiplatform")
	id("org.openapi.generator")
}

kotlin {
	/* Targets configuration omitted.
	*  To find out how to configure the targets, please follow the link:
	*  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
	jvm {}
	js {
		browser()
	}

	val kotestVersion: String by project
	val coroutinesVersion: String by project
	val serializationVersion: String by project
	val jacksonVersion: String by project

	sourceSets {

		val commonMain by getting {
			dependencies {
				implementation(kotlin("stdlib-common"))
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

				/**
				 * Зависимости ниже мы забрали из сгенерированного build.gradle. Они нужны для компиляции подпроекта
				 */
				implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
				implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
				implementation(project(":dm-transport-main-mp"))
				implementation(project(":dm-transport-main-mp"))

			}
			//Добавление сгенерированных данных для компиляции
//			kotlin.srcDir("$buildDir/generate-resources/main/src/main/kotlin")
//			kotlin.srcDir("$buildDir/generate-resources/main/src/main/kotlin")

		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))

				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
				implementation("io.kotest:kotest-assertions-core:$kotestVersion")
				implementation("io.kotest:kotest-property:$kotestVersion")
			}
		}
		val jsMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}
		val jsTest by getting {
			dependencies {
				implementation(kotlin("test-js"))
				implementation("io.kotest:kotest-framework-engine:$kotestVersion")
			}
		}
		val jvmMain by getting {
			dependencies {
				implementation(kotlin("stdlib-jdk8"))
				implementation(project(":dm-transport-main-mp"))
				implementation(project(":dm-transport-main-mp"))
			}
		}
		val jvmTest by getting {
			dependencies {
				implementation(kotlin("test-junit5"))
				implementation("io.kotest:kotest-runner-junit5:$kotestVersion")
			}
		}
	}

}

openApiGenerate {
	val openapiGroup = "${rootProject.group}.openapi"
	generatorName.set("kotlin") // Это и есть активный генератор
	packageName.set(openapiGroup)
	apiPackage.set("$openapiGroup.api")
	modelPackage.set("$openapiGroup.ru.otus.kotlin.openapi.models")
	invokerPackage.set("$openapiGroup.invoker")
	inputSpec.set("$rootDir/specs/SportProjectAPI.yaml")

	/**
	 * Здесь указываем, что нам нужны только модели, все остальное не нужно
	 */
	globalProperties.apply {
		put("ru.otus.kotlin.openapi.models", "")
		put("modelDocs", "false")
	}

	/**
	 * Настройка дополнительных параметров из документации по генератору
	 * https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin.md
	 */
	configOptions.set(
		mapOf(
			"dateLibrary" to "string",
			"enumPropertyNaming" to "UPPERCASE",
			"serializationLibrary" to "jackson",
			"collectionType" to "list"
		)
	)

	/**
	 * Так генерируется KMP версия
	 */
	library.set("multiplatform")
}
tasks {
	withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).forEach {
		it.dependsOn(openApiGenerate)
	}
}


