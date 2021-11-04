repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    jcenter()
    maven(url ="https://jitpack.io")
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)

    jvmTarget.set("1.8")

}