plugins {
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.jpa")
    id("org.flywaydb.flyway") version "9.5.1" apply true
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":challenge-core"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.flywaydb:flyway-core:9.5.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

flyway {
    url = "jdbc:postgresql://localhost:5432/customer_db"
    user = "customer"
    password = "customer"
}