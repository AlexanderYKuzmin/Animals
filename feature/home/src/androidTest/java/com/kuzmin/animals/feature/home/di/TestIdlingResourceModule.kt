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
import dagger.hilt.testing.TestInstallIn
import java.util.Optional
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [IdlingResourceModule::class]
    )
@Module
interface TestIdlingResourceModule {

    companion object {

        @Provides
        @Singleton
        fun provideIdlingResource(): CountingIdlingResource {
            return CountingIdlingResource("test_counting")
        }
    }

    @BindsOptionalOf
    fun bindIdlingResource(): CountingIdlingResource
}
