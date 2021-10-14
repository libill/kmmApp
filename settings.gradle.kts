rootProject.name = "kmmApp"

include(":androidApp")

include(":alog")
include(":utils")
include(":PlatformMMKV")
include(":business")
include(":sdkframework")

project(":sdkframework").projectDir = File(rootDir, "shared/sdkframework")
project(":alog").projectDir = File(rootDir, "shared/alog")
project(":utils").projectDir = File(rootDir, "shared/utils")
project(":PlatformMMKV").projectDir = File(rootDir, "shared/PlatformMMKV")
project(":business").projectDir = File(rootDir, "shared/business")
