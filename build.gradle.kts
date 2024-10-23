plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.springframework.boot)
    war
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
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-jdbc")
    implementation(libs.apache.commons.csv)
    implementation(libs.h2database)
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
