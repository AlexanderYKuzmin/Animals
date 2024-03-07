package com.kuzmin.animals.dataprovider.remote.util

import com.googlecode.flickrjandroid.photos.Photo
import com.kuzmin.animals.feature.api.model.AnimalPhoto

object TestPhotoUtil {
    fun createAnimalPhotoList(): List<AnimalPhoto> {
        return listOf(
            createAnimalPhoto("100", "badger"),
            createAnimalPhoto("101", "badger")
        )
    }

    fun createAnimalPhoto(id: String, animalName: String): AnimalPhoto {
        return AnimalPhoto(
            photoId = id,
            title = null,
            description = null,
            thumbNail = null,
            medium = null,
            small = null,
            animalNameEn = animalName,
            animalNameRu = "барсук"
        )
    }

    fun createFlickrPhotoList(): List<Photo> {
        return listOf(
            createFlickrPhoto("100"),
            createFlickrPhoto("101")
        )
    }

    fun createFlickrPhoto(id: String): Photo {
        return Photo().apply {
            this.id = id

        }
    }
}