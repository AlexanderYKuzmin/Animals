package com.kuzmin.animals.dataprovider.local.mapper

import com.kuzmin.animals.dataprovider.local.util.TestPhotoUtil
import com.kuzmin.animals.dataprovider.local.util.TestPhotoDbUtil
import com.kuzmin.animals.dataprovider.local.util.TestTagUtil
import com.kuzmin.animals.feature.api.model.AnimalType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DbMapperTest {

    private val mapper = DbMapper()

    @Test
    fun mapAnimalPhotoToPhotoDbFavorite() {
        val expected = TestPhotoDbUtil.createAnimalPhotoDbFavorite()

        val actual = mapper.mapAnimalPhotoToPhotoDb(
            TestPhotoUtil.createAnimalPhotoTestCommon(),
            true
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapAnimalPhotoToPhotoDbExcluded() {
        val expected = TestPhotoDbUtil.createAnimalPhotoDbExcluded()

        val actual = mapper.mapAnimalPhotoToPhotoDb(
            TestPhotoUtil.createAnimalPhotoTestCommon(),
            false
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapAnimalPhotoToPhotoDbExcludedWithNulls() {
        val expected = TestPhotoDbUtil.createAnimalPhotoDbFavoriteForPhotoWithNullFields()

        val actual = mapper.mapAnimalPhotoToPhotoDb(
            TestPhotoUtil.createAnimalPhotoTestWithNullFields(),
            true
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapPhotoDbFavoriteToAnimalPhoto() {
        val expected = TestPhotoUtil.createAnimalPhotoTestCommon()

        val actual = mapper.mapPhotoDbToAnimalPhoto(
            TestPhotoDbUtil.createAnimalPhotoDbFavorite()
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapTypeAndTagsToTagsDb() {
        val expected = TestTagUtil.getTagDbList()

        val testTags = listOf("wild", "nature")
        val testAnimalType = AnimalType.INSECT
        val actual = mapper.mapTypeAndTagsToTagsDb(
            testAnimalType,
            testTags
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapTypeAndTagsToTagsDbWrongOrderTags() {
        val expected = TestTagUtil.getTagDbList()

        val testTags = listOf("nature", "wild")
        val testAnimalType = AnimalType.INSECT
        val actual = mapper.mapTypeAndTagsToTagsDb(
            testAnimalType,
            testTags
        )
        assertNotEquals(expected, actual)
    }
}