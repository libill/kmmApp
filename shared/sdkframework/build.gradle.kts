import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "$version"

kotlin {
    android()
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

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations.get("main").kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
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

        frameworkName = "SDKFramework"

        pod("MMKV", "1.2.8")
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
            dependsOn(iosX64Main)
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(30)
    }
}

tasks.named<org.jetbrains.kotlin.gradle.tasks.DefFileTask>("generateDefMMKV").configure {
    doLast {
        println("generateDefMMKV start")
        val includeDir = File(projectDir, "build/cocoapods/synthetic/IOS/sdkframework/Pods/MMKV/iOS/MMKV/MMKV")
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