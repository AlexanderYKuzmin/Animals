package com.kuzmin.animals.core.network.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kuzmin.animals.common.firebase_resource.FirebaseContainer
import com.kuzmin.animals.core.network.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseNetworkModule {
    @Provides
    fun provideFireDataBase() = FirebaseContainer.fireDatabase

    @Provides
    fun provideFirebaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference("animals")
    }

    @Provides
    fun provideFirebaseStorageReference() = FirebaseContainer.fireStorage.reference

    @Provides
    fun provideFirebaseService(
        fireDb: FirebaseDatabase,
        storageReference: StorageReference
    ): FirebaseService {
        return FirebaseService(fireDb, storageReference)
    }
}