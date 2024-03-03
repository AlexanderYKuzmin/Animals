package com.kuzmin.animals.dataprovider.local.di

import android.content.Context
import com.kuzmin.animals.dataprovider.local.DbRepositoryImpl
import com.kuzmin.animals.dataprovider.local.datastore.PrefManagerImpl
import com.kuzmin.animals.dataprovider.local.mapper.DbMapper
import com.kuzmin.animals.feature.api.api.DbRepository
import com.kuzmin.animals.feature.api.api.PrefManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


        @Provides
        fun providePrefManager(@ApplicationContext appContext: Context): PrefManager {
            return PrefManagerImpl(appContext)
        }
    }
}