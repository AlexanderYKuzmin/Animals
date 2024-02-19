package com.kuzmin.animals

import android.app.Application
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kuzmin.animals.common.firebase_resource.FireDatabaseContainer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        FireDatabaseContainer.fireDatabase = Firebase.database
        super.onCreate()
    }
}