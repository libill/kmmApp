object Versions {
    const val kotlinGradlePlugin = "2.1.21-RC2"
    const val buildGradle = "8.10.0"

    const val startup_runtime = "1.1.0"

    const val mmkv_static = "1.2.10"
    const val mars_xlog = "1.2.6"

    const val dokka = "1.9.20"
}

object TENCENT {
    const val mmkv_static = "com.tencent:mmkv-static:${Versions.mmkv_static}"
    const val mars_xlog = "com.tencent.mars:mars-xlog:${Versions.mars_xlog}"
}

object AndroidX {
    const val startup_runtime = "androidx.startup:startup-runtime:${Versions.startup_runtime}"
}

object AndroidVersion {
    const val minSdk = 24
    const val compileSdk = 34
    const val targetSdk = 34
}