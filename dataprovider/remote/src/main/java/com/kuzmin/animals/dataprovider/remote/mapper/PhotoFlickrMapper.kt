package com.kuzmin.animals.dataprovider.remote.mapper

import android.util.Log
import com.googlecode.flickrjandroid.photos.Photo
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.feature.api.model.FlickrRequest
import javax.inject.Inject

class PhotoFlickrMapper @Inject constructor(

){
    fun mapPhotoFlickrListToAnimalPhotoList(animal: Animal, photosFlickr: List<Photo>): List<AnimalPhoto> {
        Log.d("Flickr", "mapper PHOTOS: ${photosFlickr.joinToString(",")}")
        if (photosFlickr.isEmpty()) return emptyList()
        return photosFlickr.map {
            mapPhotoFlickrToAnimalPhoto(it, animal)
        }
    }

    private fun mapPhotoFlickrToAnimalPhoto(photoFlickr: Photo, animal: Animal): AnimalPhoto {
        with(photoFlickr) {
            return AnimalPhoto(
                photoId = id,
                title = title,
                description = description,
                medium = mediumUrl,
                thumbNail = thumbnailUrl,
                animalNameEn = animal.nameEn,
                animalNameRu = animal.nameRu,
                small = smallUrl
            )
        }
    }
}