package com.kuzmin.animals.dataprovider.remote.di

import com.kuzmin.animals.dataprovider.remote.repo.FirebaseRepositoryImpl
import com.kuzmin.animals.dataprovider.remote.repo.FlickrRepositoryImpl
import com.kuzmin.animals.dataprovider.remote.mapper.DataSnapshotMapper
import com.kuzmin.animals.dataprovider.remote.mapper.PhotoFlickrMapper
import com.kuzmin.animals.feature.api.api.FirebaseRepository
import com.kuzmin.animals.feature.api.api.FlickrRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataProviderModule {

    @Binds
    @Singleton
    fun bindFirebaseRepository(firebaseRepositoryImpl: FirebaseRepositoryImpl): FirebaseRepository

    @Binds
    @Singleton
    fun bindFlickrRepository(flickrRepositoryImpl: FlickrRepositoryImpl): FlickrRepository

    companion object {
        @Provides
        @Singleton
        fun providePhotoFlickrMapper(): PhotoFlickrMapper {
            return PhotoFlickrMapper()
        }

        @Provides
        @Singleton
        fun provideDataSnapshotMapper() = DataSnapshotMapper()
    }
}