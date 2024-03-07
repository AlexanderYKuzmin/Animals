package com.kuzmin.animals.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kuzmin.animals.core.database.util.TestUtil
import org.junit.Assert.*
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AnimalDatabaseTest {

    private lateinit var animalDao: AnimalDao
    private lateinit var db: AnimalDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AnimalDatabase::class.java).build()
        animalDao = db.animalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetPhoto() = runTest {
        animalDao.addPhoto(
            TestUtil.createPhoto("102", true).copy(animalNameEn = "TestName")
        )
        val actual = animalDao.getFavoritesByName("TestName")

        assertEquals("TestName", actual[0].animalNameEn)
    }

    @Test
    fun insertPhotoNotUniqueUrls() =  runTest {
        animalDao.addPhoto(
            TestUtil.createPhoto("100", true).copy(
                mediumUrl = "xxx",
                thumbnailUrl = "yyy",
                smallUrl = "uuu"
            )
        )
        animalDao.addPhoto(
            TestUtil.createPhoto("101", true).copy(
                mediumUrl = "xxx",
                thumbnailUrl = "yyy",
                smallUrl = "uuu"
            )
        )

        val list = animalDao.getFavorites()

        assertNotEquals(2, list.size)
    }

    @Test
    fun deletePhotoById() = runTest {
        animalDao.addPhoto(TestUtil.createPhoto("100", true))

        val listAfterAdded = animalDao.getFavorites()

        animalDao.deletePhoto("100")

        val listAfterDeleted = animalDao.getFavorites()

        assertNotEquals(listAfterAdded, listAfterDeleted)
    }

    @Test
    fun getBlackListIdsByName() = runTest {
        animalDao.addPhoto(TestUtil.createPhoto("100", false))
        animalDao.addPhoto(TestUtil.createPhoto("101", true))
        animalDao.addPhoto(TestUtil.createPhoto("102", false))
        animalDao.addPhoto(TestUtil.createPhoto("103", false).copy(
            animalNameEn = "test"
        ))

        val expectedArray = arrayOf("100", "102")

        val actualArray = animalDao.getBlackListIdsByName("butterfly").toTypedArray()

        assertArrayEquals(expectedArray, actualArray)
    }

    @Test
    fun addTagsAndGetTagsByType() = runTest {
        val testTags = listOf(
            TestUtil.createTag("beast", "00"),
            TestUtil.createTag("insect", "20")
        )
        val expectedArray = testTags.map { it.nameEn }.toTypedArray()

        animalDao.addTags(testTags)

        val tags = mutableListOf<String>()
        val tagsBeast = animalDao.getTagsByAnimalType("beast")
        val tagsInsect = animalDao.getTagsByAnimalType("insect")

        tags.addAll(tagsBeast)
        tags.addAll(tagsInsect)

        val actualArray = tags.toTypedArray()

        assertArrayEquals(expectedArray, actualArray)
    }
}