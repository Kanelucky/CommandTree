plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

group = "org.kanelucky"
version = "0.1.1"

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/maven-snapshots")
    maven("https://repo.opencollab.dev/maven-releases")
    maven {
        name = "powerNukkitXReleases"
        url = uri("https://repo.powernukkitx.org/releases")
    }
    maven {
        name = "powerNukkitXSnapshots"
        url = uri("https://repo.powernukkitx.org/snapshots")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
