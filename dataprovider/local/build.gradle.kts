plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.kuzmin.animals.dataprovider.local"
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
    implementation(project(":core:database"))

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.material)

    implementation(DataStore.datastore_preferences)
    implementation(DataStore.datastore_annotations)

    implementation(DaggerHilt.hilt)
    testImplementation("junit:junit:4.12")
    kapt(DaggerHilt.hilt_compiler)

    testImplementation(Test.junit4)
    androidTestImplementation(AndroidTest.extJunit4)
    androidTestImplementation(AndroidTest.espresso)
}