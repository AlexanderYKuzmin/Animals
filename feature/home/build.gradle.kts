plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.kuzmin.animals.feature.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.kuzmin.animals.feature.home.CustomTestRunner"
        //consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:api"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.fragment)
    implementation(Deps.activity)

    implementation(DaggerHilt.hilt)
    implementation(AndroidTest.espresso_idling)
    kapt(DaggerHilt.hilt_compiler)

    implementation(platform(FireBase.firebase_platform))
    implementation(FireBase.firebase)

    implementation(LifeCycle.liveData)
    implementation(LifeCycle.viewModel)
    implementation(LifeCycle.service)

    implementation(Navigation.nav_fragment)
    implementation(Navigation.nav_ui_ktx)

    implementation(ViewPager2.view_pager_2)

    implementation(Picasso.picasso)

    debugImplementation(Test.fragment_test)

    testImplementation(Test.junit4)
    testImplementation (Test.mockito_kotlin)
    testImplementation(Test.mockito_inline)
    testImplementation (Test.coroutine_test)
    testImplementation(Test.arch_core_test)
    testImplementation(DaggerHilt.hilt_android_testing)
    testImplementation(DaggerHilt.hilt_android_compiling)
    testImplementation(AndroidTest.espresso_idling)

    androidTestImplementation(Test.arch_core_test)
    /*androidTestImplementation(Robolectric.robolectric)*/
    androidTestImplementation (DaggerHilt.hilt_android_testing)
    kaptAndroidTest (DaggerHilt.hilt_android_compiling)

    androidTestImplementation(Test.junit4)
    androidTestImplementation(AndroidTest.extJunit4)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidTest.espresso_idling)
    androidTestImplementation(Test.mockito_kotlin)
    //androidTestImplementation(Test.mockito_inline)
    androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito-inline:2.28.1")
    //androidTestImplementation(AndroidTest.mockito_android)

}