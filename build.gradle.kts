buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
        maven(url ="https://jitpack.io")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}")
        classpath("com.android.tools.build:gradle:${Versions.buildGradle}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url ="https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

plugins {
    id("org.jetbrains.dokka") version Versions.dokka
}

repositories {
    mavenCentral()
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(rootDir.resolve(".docs"))
}
