plugins {
    id("java")
    id("com.gradleup.shadow") version "9.3.1"
}

group = "org.kanelucky"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/maven-snapshots")
    maven("https://repo.opencollab.dev/maven-releases")
    maven("https://repo.powernukkitx.org/releases")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly("org.powernukkitx:server:2.0.0-SNAPSHOT")
}

tasks {

    jar {
        manifest {
            attributes(
                "Main-Class" to "org.kanelucky.server.CommandTreeMain",
                "Implementation-Title" to "CommandTree-API",
                "Implementation-Version" to project.version
            )
        }
    }

    shadowJar {
        mergeServiceFiles()
        archiveFileName.set(
            "commandtree-api-${project.version}-shaded.jar"
        )
    }

    build {
        dependsOn(shadowJar)
    }

    test {
        useJUnitPlatform()
    }
}
