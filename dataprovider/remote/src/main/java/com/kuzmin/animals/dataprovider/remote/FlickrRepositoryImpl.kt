package com.kuzmin.animals.dataprovider.remote

import android.util.Log
import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.core.network.FlickrService
import com.kuzmin.animals.dataprovider.remote.mapper.PhotoFlickrMapper
import com.kuzmin.animals.feature.home.api.FlickrRepository
import com.kuzmin.animals.feature.home.domain.model.FlickrRequest
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val flickrService: FlickrService,
    private val photoFlickrMapper: PhotoFlickrMapper
) : FlickrRepository{
    override suspend fun searchPhotos(request: FlickrRequest): List<AnimalPhoto> {
        Log.d("Flickr", "Flickr Repository: Search photo")

        return photoFlickrMapper.mapPhotoFlickrListToAnimalPhotoList(
            request,
            flickrService.search(request.tags, request.quantity)
        )
    }
}