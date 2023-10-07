plugins {
    java
    kotlin("jvm") version "1.9.10"
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("xyz.jpenilla.run-paper") version "2.1.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "de.dasphiller"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")

    implementation("de.hglabor:knockback-api:1.19.0")

    implementation(files("libs/kitapi-0.1.0.jar"))

    implementation("net.axay:kspigot:1.20.1")
}

val javaVersion = 17

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "$javaVersion"
        }
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    assemble {
        dependsOn(reobfJar)
    }
    runServer {
        minecraftVersion("1.20.1")
    }
}


/*
reobfJar {
  // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
  // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
  outputJar.set(layout.buildDirectory.file("libs/ExamplePlugin-${project.version}.jar"))
}
 */


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
}

kotlin {
    jvmToolchain(javaVersion)
}
