package com.kuzmin.animals.dataprovider.remote.mapper

import android.util.Log
import com.googlecode.flickrjandroid.photos.Photo
import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.home.domain.model.FlickrRequest
import javax.inject.Inject

class PhotoFlickrMapper @Inject constructor(

){
    fun mapPhotoFlickrListToAnimalPhotoList(request: FlickrRequest, photosFlickr: List<Photo>): List<AnimalPhoto> {
        Log.d("Flickr", "mapper PHOTOS: ${photosFlickr.joinToString(",")}")
        if (photosFlickr.isEmpty()) return emptyList()
        return photosFlickr.map {
            mapPhotoFlickrToAnimalPhoto(it, request.tags[0])
        }
    }

    private fun mapPhotoFlickrToAnimalPhoto(photoFlickr: Photo, animalName: String): AnimalPhoto {
        with(photoFlickr) {
            return AnimalPhoto(
                photoId = id,
                title = title,
                description = description,
                medium = mediumUrl,
                thumbNail = thumbnailUrl,
                animalNameEn = animalName,
                small = smallUrl
            )
        }
    }
}