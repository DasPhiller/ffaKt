plugins {
    java
    kotlin("jvm") version "2.0.10"
    id("io.papermc.paperweight.userdev") version "1.7.2"
    id("xyz.jpenilla.run-paper") version "2.1.0"
    id("com.gradleup.shadow") version "9.0.0-beta9"
}

group = "de.dasphiller"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    implementation(files("libs/kitapi-1.0.jar"))

    implementation ("fr.mrmicky:fastboard:2.1.3")

    implementation("net.axay:kspigot:1.21.0")
}

val javaVersion = 21

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
        minecraftVersion("1.21.1")
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
