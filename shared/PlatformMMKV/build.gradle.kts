plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.dokka")
}

version = "$version"

kotlin {
    android()
    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    targets {
        configure(listOf(iosX64, iosArm64)) {
        }
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations.get("main").kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"

    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        specRepos {
            url("https://github.com/CocoaPods/Specs")
            url("https://mirrors.tuna.tsinghua.edu.cn/git/CocoaPods/Specs.git")
            url("https://cdn.cocoapods.org/")
        }
        ios.deploymentTarget = "13.5"

        pod("MMKV", "1.2.8")

        framework {
            baseName = "PlatformMMKV"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":utils"))
                implementation(project(":alog"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(TENCENT.mmkv_static)
            }
        }
        val iosX64Main by getting {
            kotlin.srcDir("src/iosMain")
            dependencies {
            }
        }
        val iosArm64Main by getting {
            dependsOn(iosX64Main)
        }
    }
}

android {
    compileSdk = AndroidVersion.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidVersion.minSdk
        targetSdk = AndroidVersion.targetSdk
    }
    namespace = "com.libill.kmm.mmkv"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

tasks.named<org.jetbrains.kotlin.gradle.tasks.DefFileTask>("generateDefMMKV").configure {
    doLast {
        println("generateDefMMKV start")
        val includeDir = File(projectDir, "build/cocoapods/synthetic/IOS/Pods/MMKV/iOS/MMKV/MMKV")
        val headers = listOf("${includeDir.path}/MMKV.h")
        headers.forEach {
            println("generateDefMMKV header:$it")
        }
        outputFile.writeText("""
            language = Objective-C
            headers = ${headers.joinToString(" ")}
             """
        )
    }
}
