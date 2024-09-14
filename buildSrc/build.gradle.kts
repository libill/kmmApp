repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public")
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
//    experimentalWarning.set(false)

    jvmTarget.set("17")

}
