package com.kuzmin.animals.media.di

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.media.MediaServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediaModule {

    @Binds
    @Singleton
    fun bindMediaService(mediaServiceImpl: MediaServiceImpl): MediaService

    companion object {

        @Provides
        @Singleton
        fun provideMediaPlayer(): MediaPlayer {
            return MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
            }
        }
    }
}