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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(":feature:api"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.fragment)
    implementation(Deps.activity)

    implementation(DaggerHilt.hilt)
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

    testImplementation(Test.junit4)
    testImplementation (Test.mockito)
    testImplementation(Test.mockito_inline)
    testImplementation (Test.coroutine_test)
    testImplementation(Test.arch_core_test)
    androidTestImplementation (DaggerHilt.hilt_android_testing)
    kaptAndroidTest (DaggerHilt.hilt_android_compiling)
    androidTestImplementation(AndroidTest.extJunit4)
    androidTestImplementation(AndroidTest.espresso)
}