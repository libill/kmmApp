plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":sdkframework"))

    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
}

android {
    compileSdkVersion(AndroidVersion.compileSdk)
    defaultConfig {
        applicationId = "com.libill.kmm.android"
        minSdkVersion(AndroidVersion.minSdk)
        targetSdkVersion(AndroidVersion.targetSdk)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        pickFirst("**.kotlin_module")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}