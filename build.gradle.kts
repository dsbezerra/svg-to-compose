import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("maven-publish")
}

group = "br.com.devsrsouza"
version = "0.8.2"

repositories {
    mavenCentral()
    maven("https://maven.google.com")
    maven("https://jetbrains.bintray.com/trove4j")
}

dependencies {
    implementation("com.google.guava:guava:23.0")
    implementation("com.android.tools:sdk-common:27.2.0-alpha16")
    implementation("com.android.tools:common:27.2.0-alpha16")
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("org.ogce:xpp3:1.1.6")

    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}
