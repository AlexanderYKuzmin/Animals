package com.kuzmin.animals.core.network

import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.Photo
import com.googlecode.flickrjandroid.photos.SearchParameters
import javax.inject.Inject

class FlickrService @Inject constructor(
    private val flickr: Flickr,
    private val searchParameters: SearchParameters
) {

    fun search(
        tagsReq: List<String>,
        animalName: String,
        quantity: Int = PHOTO_QUANTITY,
        blacklist: List<String>
    ): List<Photo> {
        searchParameters.apply {
            text = animalName
            tags = mutableListOf(animalName).apply {
                addAll(
                    tagsReq.filter { it.length > 3 }
                )
            }.toTypedArray()
        }

        return flickr.photosInterface.search(searchParameters, quantity, 1)
            .filter { !blacklist.contains(it.id) }
    }

    companion object {
        const val PHOTO_QUANTITY = 20
    }
}