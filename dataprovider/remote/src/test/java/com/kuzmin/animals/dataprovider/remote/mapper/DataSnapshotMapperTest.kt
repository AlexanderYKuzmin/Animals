package com.kuzmin.animals.dataprovider.remote.mapper

import com.google.firebase.database.DataSnapshot
import com.kuzmin.animals.dataprovider.remote.model.AnimalDto
import com.kuzmin.animals.dataprovider.remote.model.FactDto
import com.kuzmin.animals.dataprovider.remote.util.TestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DataSnapshotMapperTest {
    private lateinit var iterable: Iterable<DataSnapshot>

    @Spy
    private lateinit var mapper: DataSnapshotMapper

    @Mock
    private lateinit var dataSnapshot: DataSnapshot

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        iterable = object : Iterable<DataSnapshot> {
            override fun iterator(): Iterator<DataSnapshot> {
                return listOf(dataSnapshot).iterator()
            }
        }
    }

    @Test
    fun mapDataSnapshotAnimalsToAnimalsListModel() {
        whenever(dataSnapshot.children).thenReturn(iterable)
        whenever(dataSnapshot.getValue(AnimalDto::class.java)).thenReturn(TestUtil.createAnimalDto())
        val expected = listOf(TestUtil.createAnimal())

        val actual = mapper.mapDataSnapshotAnimalsToAnimalsListModel(dataSnapshot)

        verify(dataSnapshot).children
        verify(dataSnapshot).getValue(AnimalDto::class.java)

        assertEquals(expected, actual)
    }

    @Test
    fun mapAnimalDtoToAnimal() {
        val methodMapAnimalDtoToAnimal =
            mapper.javaClass.getDeclaredMethod("mapAnimalDtoToAnimal", AnimalDto::class.java)
        methodMapAnimalDtoToAnimal.isAccessible = true
        val parameters = arrayOf(TestUtil.createAnimalDto())

        val expected = TestUtil.createAnimal()

        val actual = methodMapAnimalDtoToAnimal.invoke(mapper, *parameters)

        assertEquals(expected, actual)
    }

    @Test
    fun mapFactsDataSnapshotToFactsListModel() {
        whenever(dataSnapshot.children).thenReturn(iterable)
        whenever(dataSnapshot.getValue(FactDto::class.java)).thenReturn(TestUtil.createFactDto())
        val expected = listOf(TestUtil.createFact())

        val actual = mapper.mapFactsDataSnapshotToFactsListModel(dataSnapshot)

        verify(dataSnapshot).children
        verify(dataSnapshot).getValue(FactDto::class.java)

        assertEquals(expected, actual)
    }

    @Test
    fun mapFactDtoToFact() {
        val methodMapFactDtoToFact
            = mapper.javaClass.getDeclaredMethod("mapFactDtoToFact", FactDto::class.java)
        methodMapFactDtoToFact.isAccessible = true
        val parameters = arrayOf(TestUtil.createFactDto())

        val expected = TestUtil.createFact()

        val actual = methodMapFactDtoToFact.invoke(mapper, *parameters)

        assertEquals(expected, actual)
    }
}