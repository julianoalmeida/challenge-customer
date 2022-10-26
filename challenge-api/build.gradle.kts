plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

apply(plugin = "org.springframework.boot")
apply(plugin = "application")

dependencies {

    implementation(project(":challenge-core"))
    implementation(project(":challenge-database"))
    implementation(project(":challenge-producer"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
