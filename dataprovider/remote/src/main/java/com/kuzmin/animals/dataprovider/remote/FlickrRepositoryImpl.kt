package com.kuzmin.animals.dataprovider.remote

import com.kuzmin.animals.core.network.FlickrService
import com.kuzmin.animals.dataprovider.remote.mapper.PhotoFlickrMapper
import com.kuzmin.animals.feature.api.api.FlickrRepository
import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.feature.api.model.FlickrRequest
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val flickrService: FlickrService,
    private val photoFlickrMapper: PhotoFlickrMapper
) : FlickrRepository {
    override suspend fun searchPhotos(request: FlickrRequest): List<AnimalPhoto> {
        return photoFlickrMapper.mapPhotoFlickrListToAnimalPhotoList(
            request.animal,
            flickrService.search(
                tagsReq = request.tags,
                animalName = request.animal.nameEn,
                quantity = request.quantity,
                blacklist = request.blacklist)
        )
    }
}