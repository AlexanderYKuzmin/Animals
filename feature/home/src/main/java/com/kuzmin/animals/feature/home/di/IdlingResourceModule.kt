package com.kuzmin.animals.feature.home.di

import androidx.test.espresso.idling.CountingIdlingResource
import com.kuzmin.animals.feature.home.ui.viewmodels.HomeViewModel
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.components.ViewWithFragmentComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface IdlingResourceModule {

    @BindsOptionalOf
    fun provideIdlingResource(): CountingIdlingResource
}
