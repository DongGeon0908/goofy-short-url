import Build_gradle.DependencyVersion.KOTLIN_LOGGING_VERSION
import Build_gradle.DependencyVersion.SWAGGER_VERSION
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    kotlin("plugin.allopen") version "1.6.21"
    idea
}

group = "com.goofy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

object DependencyVersion {
    const val KOTLIN_LOGGING_VERSION = "3.0.0"
    const val SWAGGER_VERSION = "2.10.5"
}

dependencies {
    // spring starter
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // jetbrains
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    // swagger
    if (isSnapshotVersion) {
        implementation("io.springfox:springfox-swagger2:$SWAGGER_VERSION")
        implementation("io.springfox:springfox-swagger-ui:$SWAGGER_VERSION")
        implementation("io.springfox:springfox-spring-webmvc:$SWAGGER_VERSION")
    }

    // kotlin logger
    implementation("io.github.microutils:kotlin-logging-jvm:$KOTLIN_LOGGING_VERSION")

    // mysql
    runtimeOnly("mysql:mysql-connector-java")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val Project.isSnapshotVersion: Boolean get() = version.toString().endsWith("SNAPSHOT")
