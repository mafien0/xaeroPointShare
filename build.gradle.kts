import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.3.10"
    id("fabric-loom") version "1.15-SNAPSHOT"
    id("maven-publish")
}

version = property("mod_version")!!
group = property("maven_group")!!

base {
    archivesName.set(property("archives_base_name") as String)
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("kotlin_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
}

tasks.processResources {
    inputs.property("version", project.version)
    
    filesMatching("fabric.mod.json") {
        expand(mapOf(
            "version" to project.version,
            "minecraft_version" to property("minecraft_version"),
            "loader_version" to property("loader_version"),
            "kotlin_loader_version" to property("kotlin_loader_version")
        ))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(targetJavaVersion)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = targetJavaVersion.toString()
    }
}

tasks.jar {
    from("LICENSE.txt") {
        rename { "${it}_${base.archivesName.get()}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = property("archives_base_name") as String
            from(components["java"])
        }
    }

    repositories {
        // Add repositories to publish to here.
    }
}
