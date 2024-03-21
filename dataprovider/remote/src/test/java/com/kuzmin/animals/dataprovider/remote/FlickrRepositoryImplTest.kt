package com.kuzmin.animals.dataprovider.remote

import com.kuzmin.animals.core.network.FlickrService
import com.kuzmin.animals.dataprovider.remote.mapper.PhotoFlickrMapper
import com.kuzmin.animals.dataprovider.remote.repo.FlickrRepositoryImpl
import com.kuzmin.animals.dataprovider.remote.util.TestPhotoUtil
import com.kuzmin.animals.dataprovider.remote.util.TestUtil
import com.kuzmin.animals.feature.api.api.FlickrRepository
import com.kuzmin.animals.feature.api.model.FlickrRequest
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FlickrRepositoryImplTest {
    private var flickrRepositoryImpl: FlickrRepository? = null

    @Mock
    private lateinit var flickrRequest: FlickrRequest

    @Mock
    private lateinit var flickrService: FlickrService

    @Mock
    private lateinit var photoFlickrMapper: PhotoFlickrMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        flickrRepositoryImpl = FlickrRepositoryImpl(flickrService, photoFlickrMapper)
    }

    @After
    fun tearDown() {
        flickrRepositoryImpl = null
    }

    @Test
    fun searchPhotos() {
        runTest {
            whenever(flickrService.search(any(), anyString(), anyInt(), any()))
                .thenReturn(TestPhotoUtil.createFlickrPhotoList())
            whenever(flickrRequest.animal).thenReturn(TestUtil.createAnimal())

            flickrRepositoryImpl!!.searchPhotos(flickrRequest)

            verify(flickrService).search(any(), anyString(), anyInt(), any())
            verify(flickrRequest, times(2)).animal
        }
    }
}