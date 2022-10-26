import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    id("org.springframework.boot") version "2.7.2" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10" apply false
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.10" apply false
}

extra["springCloudVersion"] = "2021.0.3"
extra["exceptionsHandlerVersion"] = "0.1.15"
extra["mockitoKotlinVersion"] = "2.2.0"
extra["mockitoVersion"] = "3.6.28"
extra["junitJupiterVersion"] = "5.7.1"
extra["cucumberVersion"] = "6.9.0"
extra["wiremockVersion"] = "2.27.2"
extra["avroSerializerVersion"] = "7.0.1"
extra["hibernateTypesVersion"] = "2.10.4"
extra["slf4jVersion"] = "1.7.36"
extra["coroutinesVersion"] = "1.4.3"
extra["log4j2.version"] = "2.16.0"
extra["sonarqubeVersion"] = "3.4.0.2513"
extra["mockKVersion"] = "1.13.0"

group = "org.challenge.customer"
version = "0.0.1-SNAPSHOT"

allprojects {

    group = "org.challenge.customer"
    version = "1.0.0"

    configurations {
        all {
            resolutionStrategy.eachDependency {
            }
        }
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
        options.encoding = "UTF-8"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "idea")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
        "implementation"(kotlin("stdlib"))
        "testImplementation"(kotlin("test-junit"))
    }
}