package com.kuzmin.animals.core.network.util

import com.googlecode.flickrjandroid.photos.Photo
import com.googlecode.flickrjandroid.photos.PhotoList

object TestUtilFlickr {
    fun createPhotoList(): PhotoList {
        return PhotoList().apply {
            add(
                createPhoto("100")
            )
            add(
                createPhoto("101")
            )
            pages = 1
            total = 2
        }
    }

    fun createPhoto(id: String): Photo {
        return Photo().apply {
            this.id = id
        }
    }
}