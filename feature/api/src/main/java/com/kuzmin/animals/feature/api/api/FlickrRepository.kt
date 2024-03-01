package com.kuzmin.animals.feature.api.api

import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.feature.api.model.FlickrRequest

interface FlickrRepository {
    suspend fun searchPhotos(request: FlickrRequest): List<AnimalPhoto>
}