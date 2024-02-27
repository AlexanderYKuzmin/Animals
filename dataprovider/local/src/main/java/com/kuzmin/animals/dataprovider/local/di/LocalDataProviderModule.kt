package com.kuzmin.animals.dataprovider.local.di

import com.kuzmin.animals.dataprovider.local.DbRepositoryImpl
import com.kuzmin.animals.dataprovider.local.mapper.DbMapper
import com.kuzmin.animals.feature.home.api.DbRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataProviderModule {
    @Binds
    fun bindDbRepository(dbRepositoryImpl: DbRepositoryImpl): DbRepository

    companion object {
        @Provides
        @Singleton
        fun provideDbMapper() = DbMapper()
    }
}