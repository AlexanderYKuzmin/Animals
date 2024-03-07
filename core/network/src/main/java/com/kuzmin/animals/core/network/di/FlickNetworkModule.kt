package com.kuzmin.animals.core.network.di

import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.SearchParameters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val API_KEY = "cd278152e8d3e7e92270d7ddb0d41721"

@Module
@InstallIn(SingletonComponent::class)
class FlickNetworkModule {

    @Provides
    fun provideFlickr() = Flickr(API_KEY)

    @Provides
    fun provideSearchParams(): SearchParameters {
        return SearchParameters().apply {
            media = "photos"
            tagMode = "all"
            setExtras(setOf("url_c", "url_z", "url_t", "url_s"))
        }
    }
}