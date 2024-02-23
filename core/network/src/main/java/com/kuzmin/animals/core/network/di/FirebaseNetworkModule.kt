package com.kuzmin.animals.core.network.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kuzmin.animals.common.firebase_resource.FireDatabaseContainer
import com.kuzmin.animals.core.network.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseNetworkModule {
    @Provides
    fun provideFireDataBase(): FirebaseDatabase {
        return FireDatabaseContainer.fireDatabase
    }

    @Provides
    fun provideFirebaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference("animals")
    }

    @Provides
    fun provideFirebaseService(fireDb: FirebaseDatabase): FirebaseService {
        return FirebaseService(fireDb)
    }
}