package com.kuzmin.animals.dataprovider.remote.mapper

import com.googlecode.flickrjandroid.photos.Photo
import com.kuzmin.animals.dataprovider.remote.model.AnimalDto
import com.kuzmin.animals.dataprovider.remote.util.TestPhotoUtil
import com.kuzmin.animals.dataprovider.remote.util.TestUtil
import com.kuzmin.animals.feature.api.model.Animal
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoFlickrMapperTest {

    @Spy
    private lateinit var mapper: PhotoFlickrMapper

    private lateinit var animal: Animal

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        animal = TestUtil.createAnimal().copy(nameEn = "badger")
    }

    @Test
    fun `map flickr Photo list to AnimalPhoto list`() {
        val flickrPhotos = TestPhotoUtil.createFlickrPhotoList()

        val actual = mapper.mapPhotoFlickrListToAnimalPhotoList(animal, flickrPhotos)

        assertTrue(actual[0].photoId == "100" && actual[0].animalNameEn == "badger")
        assertTrue(actual[1].photoId == "101" && actual[1].animalNameEn == "badger")
    }

    @Test
    fun `should return empty list`() {
        val flickrPhotos = emptyList<Photo>()

        val actual = mapper.mapPhotoFlickrListToAnimalPhotoList(animal, flickrPhotos)

        assertTrue(actual.isEmpty())
    }
}