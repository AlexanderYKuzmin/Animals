package com.kuzmin.animals.core.network

import android.util.Log
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
            tags = mutableListOf(animalName).apply { addAll(tagsReq) }.toTypedArray()
        }

        Log.d("Flickr", "Blacklist: ${blacklist.joinToString("::")}")

        return flickr.photosInterface.search(searchParameters, quantity, 1)
            .filter {
                Log.d("Flickr", "flickr id: ${it.id}, title: ${it.title}")
                !blacklist.contains(it.id) }
    }

    companion object {
        const val PHOTO_QUANTITY = 20
    }
}