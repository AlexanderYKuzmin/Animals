
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.kuzmin.animals"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kuzmin.animals"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":dataprovider:remote"))
    implementation(project(":dataprovider:local"))
    implementation(project(":media"))

    implementation(project(":feature:home"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:api"))


    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)

    implementation(platform(FireBase.firebase_platform))
    implementation(FireBase.firebase)
    implementation(FireBase.firebase_storage)
    implementation(FireBase.firebase_analytics)

    implementation(Navigation.nav_fragment)
    implementation(Navigation.nav_ui_ktx)

    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hilt_compiler)

    debugImplementation(Test.fragment_test)

    androidTestImplementation(Test.arch_core_test)
    /*androidTestImplementation(Robolectric.robolectric)*/
    androidTestImplementation (DaggerHilt.hilt_android_testing)
    kaptAndroidTest (DaggerHilt.hilt_android_compiling)
    androidTestImplementation(Test.junit4)
    androidTestImplementation(AndroidTest.extJunit4)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidTest.espresso_idling)
    androidTestImplementation(Test.mockito_kotlin)
    /*androidTestImplementation(Test.mockito_inline)*/
    androidTestImplementation(AndroidTest.mockito_android)
}