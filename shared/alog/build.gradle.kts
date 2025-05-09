import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

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
            binaries.framework {
                baseName = "alog"
            }
        }
    }
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
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
            baseName = "alog"
        }

        pod("CocoaLumberjack")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":utils"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(TENCENT.mars_xlog)
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
    namespace = "com.libill.kmm.alog"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}