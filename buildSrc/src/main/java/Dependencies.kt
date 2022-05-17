object Versions {
    const val kotlinGradlePlugin = "1.7.0-Beta"
    const val buildGradle = "4.1.3"

    const val startup_runtime = "1.1.0"

    const val mmkv_static = "1.2.10"
    const val mars_xlog = "1.2.6"

    const val dokka = "1.6.20"
}

object TENCENT {
    val mmkv_static = "com.tencent:mmkv-static:${Versions.mmkv_static}"
    val mars_xlog = "com.tencent.mars:mars-xlog:${Versions.mars_xlog}"
}

object AndroidX {
    val startup_runtime = "androidx.startup:startup-runtime:${Versions.startup_runtime}"
}