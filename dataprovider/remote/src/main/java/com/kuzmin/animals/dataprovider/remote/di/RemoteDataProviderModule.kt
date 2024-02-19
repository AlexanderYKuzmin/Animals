package com.kuzmin.animals.dataprovider.remote.di

import com.kuzmin.animals.dataprovider.remote.FirebaseRepositoryImpl
import com.kuzmin.animals.dataprovider.remote.mapper.DataSnapshotMapper
import com.kuzmin.animals.dataprovider.remote.mapper.DtoToModelMapper
import com.kuzmin.animals.feature.home.api.FirebaseRepository
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
    fun bindFirebaseRepository(firebaseRepositoryImpl: FirebaseRepositoryImpl): FirebaseRepository

    companion object {
        /*@Provides
        fun provideDtoMapper(): DtoToModelMapper {
            return DtoToModelMapper()
        }*/

        @Provides
        @Singleton
        fun provideDataSnapshotMapper() = DataSnapshotMapper()
    }
}