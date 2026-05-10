plugins {
    id("java")
    `maven-publish`
}

allprojects {
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
}

tasks.jar {
    enabled = false
}
