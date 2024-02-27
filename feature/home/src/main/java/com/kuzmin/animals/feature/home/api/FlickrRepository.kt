package com.kuzmin.animals.feature.home.api

import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.home.domain.model.FlickrRequest

interface FlickrRepository {
    suspend fun searchPhotos(request: FlickrRequest): List<AnimalPhoto>
}