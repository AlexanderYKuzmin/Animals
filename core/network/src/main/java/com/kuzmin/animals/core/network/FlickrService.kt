package com.kuzmin.animals.core.network

import android.util.Log
import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.Photo
import com.googlecode.flickrjandroid.photos.SearchParameters
import javax.inject.Inject

private const val API_KEY = "cd278152e8d3e7e92270d7ddb0d41721"

class FlickrService @Inject constructor(
    //private val flickr: Flickr,
    //private val searchParameters: SearchParameters
) {

    fun search(tagsReq: List<String>, quantity: Int = PHOTO_QUANTITY): List<Photo> {
    Log.d("Flickr", "Flickr service: Start search")
        val flickr = Flickr(API_KEY) // set in datastore
        val searchParameters = SearchParameters().apply {
            media = "photos"
            tags = tagsReq.toTypedArray()
            tagMode = "all"
            text = tags[0]
            setExtras(setOf("url_c", "url_z"))
        }

        return flickr.photosInterface.search(searchParameters, quantity, 1)
            //.filter { it.title.lowercase().contains(text) }
            /*.map {
                Log.d("Flickr", "Photo: ${it.thumbnailUrl} ::: ${it.mediumUrl}")
                it.mediumUrl }*/
    }

    companion object {
        const val PHOTO_QUANTITY = 20
    }
}