buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url ="https://jitpack.io")
        maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}")
        classpath("com.android.tools.build:gradle:${Versions.buildGradle}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}")
    }
    configurations.all {
        resolutionStrategy {
        }
        resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url ="https://jitpack.io")
        maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public")
    }
    configurations.all {
        resolutionStrategy {
        }
        resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
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
