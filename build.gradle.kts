plugins {
    id("java")
    `maven-publish`
}

allprojects {
    group = "org.kanelucky"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

tasks.jar {
    enabled = false
}
