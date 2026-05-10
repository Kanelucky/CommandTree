plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

group = "org.kanelucky.core"
version = "0.1.1"

dependencies {
    compileOnly("org.powernukkitx:server:2.0.0-SNAPSHOT")
}

tasks.jar {
    archiveBaseName.set("CommandTree-API")
    archiveVersion.set(project.version.toString())
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "CommandTree-API"
        }
    }
}
