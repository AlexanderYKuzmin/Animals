package com.kuzmin.animals.feature.home.api

interface FlickrRepository {
    suspend fun searchPhotos(tag: String): List<String>
}