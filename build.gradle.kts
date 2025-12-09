plugins {
    `java-library`
    `maven-publish`
}

group = "xyz.vintageforge"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.cleanroommc.com/snapshots").content {
        includeGroup("org.lwjgl3")
    }
}

dependencies {

    listOf("lwjgl3-glfw", "lwjgl3-jemalloc", "lwjgl3-openal", "lwjgl3-opengl", "lwjgl3-stb", "lwjgl3-tinyfd", "lwjgl3").forEach {
        compileOnly("org.lwjgl3:$it:3.3.4-27-CLEANROOM")
    }

    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("org.apache.commons:commons-lang3:3.19.0")
    compileOnly(project(":earlyconfig"))

}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release = 21
    }
}

publishing {

    publications {
        create<MavenPublication>("maven") {
            artifactId = "lwjglx"
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("https://repo.rafi67000.xyz/vintageforge")
            name = "VintageForge"
            credentials(PasswordCredentials::class)
            authentication { create<BasicAuthentication>("basic") }
        }
    }
}