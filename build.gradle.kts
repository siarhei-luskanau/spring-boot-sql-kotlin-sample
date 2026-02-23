plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.springframework.boot)
}

group = "siarhei.luskanau.spring.bootsql.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion =
            JavaLanguageVersion.of(
                libs.versions.java.languageVersion
                    .get()
                    .toInt(),
            )
    }
}

allprojects {
    apply(from = "$rootDir/ktlint.gradle")
    apply(plugin = "dev.detekt")
    detekt {
        parallel = true
        ignoreFailures = false
    }
}

dependencies {
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.apache.commons.csv)
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter")
    testImplementation("org.testcontainers:testcontainers-mysql")
    testImplementation(libs.mockito.kotlin)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
