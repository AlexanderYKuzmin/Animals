package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.home.api.FlickrRepository
import javax.inject.Inject

class GetPhotoUrlListUseCase @Inject constructor(
    private val flickrRepository: FlickrRepository
) {
    suspend operator fun invoke(tag: String) = flickrRepository.searchPhotos(tag)
}