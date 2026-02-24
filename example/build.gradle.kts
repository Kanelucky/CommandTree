plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.3.1"
}

group = "org.kanelucky"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/maven-snapshots")
    maven("https://repo.opencollab.dev/maven-releases")
    maven {
        name = "powerNukkitXReleases"
        url = uri("https://repo.powernukkitx.org/releases")
    }
}

dependencies {
    compileOnly("org.powernukkitx:server:2.0.0-SNAPSHOT")
    implementation(project(":core"))
}

tasks.jar {
    enabled = false
}

tasks {

    jar {
        manifest {
            attributes(
                "Main-Class" to "org.kanelucky.Main",
                "Implementation-Title" to "CommandTree-Example",
                "Implementation-Version" to project.version
            )
        }
    }

    shadowJar {
        mergeServiceFiles()
        archiveFileName.set(
            "CommandTree-Example-${project.version}.jar"
        )
    }

    build {
        dependsOn(shadowJar)
    }

    test {
        useJUnitPlatform()
    }
}

