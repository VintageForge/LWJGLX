plugins {
    `java-library`
    `maven-publish`
}

group = "xyz.vintageforge"
version = "1.0.3"

repositories {
    mavenCentral()
}

dependencies {

    listOf("lwjgl-glfw", "lwjgl-jemalloc", "lwjgl-openal", "lwjgl-opengl", "lwjgl-stb", "lwjgl-tinyfd", "lwjgl").forEach {
        compileOnly("org.lwjgl:$it:3.3.6")
    }

    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("org.apache.commons:commons-lang3:3.19.0")
    compileOnly("com.github.oshi:oshi-core:6.5.0")
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
            artifactId = "lwjglxx"
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