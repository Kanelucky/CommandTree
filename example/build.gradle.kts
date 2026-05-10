plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.3.1"
}

group = "org.example.exampleplugin"
version = "0.1.0"

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
                "Main-Class" to "org.example.exampleplugin.Main",
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

