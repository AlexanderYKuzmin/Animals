
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.kuzmin.animals.dataprovider.remote"
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

    implementation(project(":feature:api"))
    implementation(project(":core:network"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)

    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hilt_compiler)

    implementation(FireBase.firebase_platform)
    implementation(FireBase.firebase)

    implementation("com.googlecode.flickrj-android:flickrj-android:2.1.0")
    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.11")

    testImplementation(Test.junit4)
    testImplementation (Test.mockito)
    testImplementation(Test.mockito_inline)
    testImplementation (Test.coroutine_test)
    androidTestImplementation (DaggerHilt.hilt_android_testing)
    kaptAndroidTest (DaggerHilt.hilt_android_compiling)
    androidTestImplementation(AndroidTest.extJunit4)
    androidTestImplementation(AndroidTest.espresso)
}