import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
    id("org.openapi.generator")
	kotlin("plugin.serialization") version "1.5.20"
}

val generatedSourcesDir = "$buildDir/generated"

kotlin {
	/* Targets configuration omitted.
	*  To find out how to configure the targets, please follow the link:
	*  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
	jvm()
	js {
		browser()
		nodejs()
	}

	sourceSets {
		val serializationVersion: String by project

		val commonMain by getting {
//            kotlin.srcDirs("$generatedSourcesDir/src/commonMain/kotlin")
			dependencies {
				implementation(kotlin("stdlib-common"))
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
				kotlin.srcDir("$buildDir/generated/src/commonMain/kotlin/ru/ru/otus/kotlin/kmp/transport/models/")
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}
		val jsMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}
		val jsTest by getting {
			dependencies {
				implementation(kotlin("test"))
				implementation(kotlin("test-js"))
			}
		}
		val jvmMain by getting {
			dependencies {
				implementation(kotlin("stdlib"))
			}
		}
		val jvmTest by getting {
			dependencies {
				implementation(kotlin("test"))
				implementation(kotlin("test-junit"))
			}
		}
	}
}
/**
 * Настраиваем генерацию здесь
 * Пока KMP генерируется не так как надо - дискриминатор не учитывается
 */
openApiGenerate {
        val openapiGroup = "${rootProject.group}.kmp.transport"
        generatorName.set("kotlin") // Это и есть активный генератор
        library.set("multiplatform") // Используем библиотеку для KMP
        outputDir.set(generatedSourcesDir)
        packageName.set(openapiGroup)
        apiPackage.set("$openapiGroup.api")
        modelPackage.set("$openapiGroup.models")
        invokerPackage.set("$openapiGroup.invoker")
	inputSpec.set("$rootDir/specs/SportProjectAPI.yaml")

        /**
         * Здесь указываем, что нам нужны только модели, все остальное не нужно
         */
        globalProperties.apply {
            put("models", "")
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
                "collectionType" to "list"
            )
        )

    }
/**
 * Устанавливаем зависимость компиляции от генерации исходников. Компиляция начнется только после генерации
 */
tasks {
    withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).forEach {
        it.dependsOn(openApiGenerate)
    }
}
//dependencies {
//    implementation(kotlin("stdlib-jdk8"))
//}

//repositories {
//    mavenCentral()
//}
//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}
//val compileTestKotlin: KotlinCompile by tasks
//compileTestKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}