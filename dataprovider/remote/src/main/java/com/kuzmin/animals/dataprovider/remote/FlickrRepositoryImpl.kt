package com.kuzmin.animals.dataprovider.remote

import android.util.Log
import com.kuzmin.animals.core.network.FlickrService
import com.kuzmin.animals.feature.home.api.FlickrRepository
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val flickrService: FlickrService
) : FlickrRepository{
    override suspend fun searchPhotos(tag: String): List<String> {
        Log.d("Flickr", "Flickr Repository: Search photo")
        val photos = flickrService.search(tag)
        Log.d("Flickr", "Flickr Repository: Photo list: $photos")
        return photos
    }
}