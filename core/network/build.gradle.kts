plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.kuzmin.animals.core.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":common"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)

    implementation(platform(FireBase.firebase_platform))
    implementation(FireBase.firebase)
    implementation(FireBase.firebase_storage)

    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hilt_compiler)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofit_converter_gson)

    implementation(Flickr.flickrj_android)

    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.11")

    testImplementation(Test.junit4)
    testImplementation (Test.mockito_kotlin)
    testImplementation(Test.mockito_inline)
    androidTestImplementation (DaggerHilt.hilt_android_testing)
    kaptAndroidTest (DaggerHilt.hilt_android_compiling)
    androidTestImplementation(AndroidTest.extJunit4)
    androidTestImplementation(AndroidTest.espresso)
}