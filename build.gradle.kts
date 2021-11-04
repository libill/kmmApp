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