package com.kuzmin.animals.core.network

import android.util.Log
import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.REST
import com.googlecode.flickrjandroid.photos.SearchParameters
import javax.inject.Inject
private const val API_KEY = "cd278152e8d3e7e92270d7ddb0d41721"
class FlickrService @Inject constructor(
    //private val flickr: Flickr,
    //private val searchParameters: SearchParameters
) {

    suspend fun search(text: String): List<String> {
    Log.d("Flickr", "Flickr service: Start search")
        val flickr = Flickr(API_KEY)
        Log.d("Flickr", "Flickr service: Flickr object: $flickr")
        val searchParameters = SearchParameters().apply {
            media = "photos"
            tags = arrayOf(text)
            tagMode = "all"
            setText(text)
            setExtras(setOf("url_c", "url_z"))
        }
        /*val ph = flickr.photosInterface
            .search(searchParameters, 5, 1).forEach{
                Log.d("Flickr", "Photo sec: ${it.secret}")
                Log.d("Flickr", "Photo url: ${it.url}")
                Log.d("Flickr", "Photo large url: ${it.large1600Url}")
            }*/
        //Log.d("Flickr", "Photo url: ${ph.originalUrl}")

        return flickr.photosInterface
            .search(searchParameters, 100, 1)
            .filter {
                it.title.lowercase().contains(text)
            }
            .map {
                Log.d("Flickr", "Photo description: ${it.description}")
                Log.d("Flickr", "Photo title: ${it.title}")
                it.mediumUrl
            }
    }
}