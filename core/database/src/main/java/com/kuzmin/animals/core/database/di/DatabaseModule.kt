package com.kuzmin.animals.core.database.di

import android.content.Context
import com.kuzmin.animals.core.database.AnimalDao
import com.kuzmin.animals.core.database.AnimalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AnimalDatabase {
        return AnimalDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDao(animalDatabase: AnimalDatabase): AnimalDao {
        return animalDatabase.tmDao()
    }
}