plugins {
    id("java")
    `maven-publish`
}

allprojects {
    group = "org.kanelucky"
    version = "0.1.1"

    repositories {
        mavenCentral()
    }
}

tasks.jar {
    enabled = false
}
