import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.dokka")
}

version = "$version"

kotlin {
    androidTarget()
    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    targets {
        configure(listOf(iosX64, iosArm64)) {
            binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java) {
                export(project(":utils"))
                export(project(":alog"))
                export(project(":PlatformMMKV"))
                export(project(":business"))
            }
        }
    }

    val xcf = XCFramework()
    val iosTargets = listOf(iosX64, iosArm64)

    iosTargets.forEach {
        it.binaries.framework {
            baseName = "PlatformMMKV"
            xcf.add(this)
        }
    }

//    // Create a task to build a kmmfeedback framework.
//    tasks.register<FatFrameworkTask>("releaseIOSFramework") {
//        // The fat framework must have the same base name as the initial frameworks.
//        baseName = "SDKFramework"
//        // The default destination directory is "<build directory>/fat-framework".
//        destinationDir = buildDir.resolve("ios_frameworks")
//        // Specify the frameworks to be merged.
//        from(
//            iosX64.binaries.getFramework("RELEASE"),
//            iosArm64.binaries.getFramework("RELEASE")
//        )
//    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.CInteropProcess::class.java) {
        settings.extraOpts(listOf("-compiler-option", "-DNS_FORMAT_ARGUMENT(A)="))
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

        framework {
            baseName = "SDKFramework"
        }

        pod("MMKV", "2.2.1")
        pod("CocoaLumberjack")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":utils"))
                api(project(":alog"))
                api(project(":PlatformMMKV"))
                api(project(":business"))
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
        val iosX64Main by getting {
            kotlin.srcDir("src/iosMain")
            dependencies {
            }
        }
        val iosArm64Main by getting {
            kotlin.srcDir("src/iosMain")
            dependencies {
            }
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
    namespace = "com.libill.kmm.sdkframework"

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
            println("generateDefMMKV hearder:$it")
        }
        outputFile.writeText("""
            language = Objective-C
            headers = ${headers.joinToString(" ")}
             """
        )
    }
}