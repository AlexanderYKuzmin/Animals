package com.kuzmin.animals.core.network

import com.googlecode.flickrjandroid.Flickr
import com.googlecode.flickrjandroid.photos.PhotosInterface
import com.googlecode.flickrjandroid.photos.SearchParameters
import com.kuzmin.animals.core.network.util.TestUtilFlickr
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class FlickrServiceTest {
    private var flickrService: FlickrService? = null

    @Mock
    private lateinit var flickr: Flickr

    @Mock
    private lateinit var searchParameters: SearchParameters

    @Mock
    private lateinit var flickrPhotosInterface: PhotosInterface

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        flickrService = FlickrService(flickr, searchParameters)
    }

    @After
    fun tearDown() {
        flickrService = null
    }

    @Test
    fun `search photos excepting blacklist`() {
        whenever(flickr.photosInterface).thenReturn(flickrPhotosInterface)
        whenever(flickrPhotosInterface.search(any(SearchParameters::class.java), anyInt(), anyInt()))
            .thenReturn(TestUtilFlickr.createPhotoList())
        val blacklist = listOf("100")

        val expected = TestUtilFlickr.createPhoto("101")

        val actual = flickrService!!.search(
            listOf("tag"),
            "Badger",
            20,
            blacklist
        )

        verify(flickr.photosInterface).search(any(SearchParameters::class.java), anyInt(), anyInt())
        assertEquals(expected.id, actual.first().id)
    }
}