package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.FlickrRepository
import com.kuzmin.animals.feature.api.model.FlickrRequest
import javax.inject.Inject

class GetPhotoListUseCase @Inject constructor(
    private val flickrRepository: FlickrRepository
) {
    suspend operator fun invoke(request: FlickrRequest) =
        flickrRepository.searchPhotos(request)
}