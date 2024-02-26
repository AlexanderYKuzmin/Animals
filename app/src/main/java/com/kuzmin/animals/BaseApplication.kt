package com.kuzmin.animals

import android.app.Application
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kuzmin.animals.common.firebase_resource.FirebaseContainer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        FirebaseContainer.fireDatabase = Firebase.database
        FirebaseContainer.fireStorage = Firebase.storage
        super.onCreate()
    }
}