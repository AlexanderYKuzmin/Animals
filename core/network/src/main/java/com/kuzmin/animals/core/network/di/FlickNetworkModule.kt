package com.kuzmin.animals.core.network.di

import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.SearchParameters
import com.kuzmin.animals.core.network.FlickApiService
import com.kuzmin.animals.core.network.interceptors.FlickRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://176.119.159.44/api/v1/"

private const val API_KEY = "cd278152e8d3e7e92270d7ddb0d41721"
@Module
@InstallIn(SingletonComponent::class)
class FlickNetworkModule {

    @Provides
    fun provideOkHttpClient(requestInterceptor: FlickRequestInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): FlickApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FlickApiService::class.java)
    }

    /*@Provides
    fun provideFlickr(): Flickr {
        return Flickr(API_KEY)
    }*/

    /*@Provides
    fun provideSearchParams(): SearchParameters {
        return SearchParameters().apply {
            media = "photos"
        }
    }*/
}