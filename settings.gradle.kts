pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Animals"
include(":app")
include(":feature:home")
include(":feature:favorite")
include(":feature:settings")
include(":common")
include(":core:network")
include(":core:database")
include(":dataprovider:remote")
include(":dataprovider:local")
