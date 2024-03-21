package com.kuzmin.animals.dataprovider.remote

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.kuzmin.animals.core.network.FirebaseService
import com.kuzmin.animals.dataprovider.remote.mapper.DataSnapshotMapper
import com.kuzmin.animals.dataprovider.remote.repo.FirebaseRepositoryImpl
import com.kuzmin.animals.feature.api.api.FirebaseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FirebaseRepositoryImplTest {
    private var repository: FirebaseRepository? = null

    @Mock
    private lateinit var firebaseService: FirebaseService

    @Mock
    private lateinit var mapper: DataSnapshotMapper

    @Mock
    private lateinit var dataSnapShot: DataSnapshot

    @Mock
    private lateinit var deferredDataSnapShot: Deferred<DataSnapshot>

    @Mock
    private lateinit var deferredUri: Deferred<Uri>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FirebaseRepositoryImpl(mapper, firebaseService)
    }

    @After
    fun tearDown() {
        repository = null
    }

    @Test
    fun getAllAnimals() = runTest {
        `when`(firebaseService.getAllAnimals()).thenReturn(deferredDataSnapShot)
        `when`(deferredDataSnapShot.await()).thenReturn(dataSnapShot)
        `when`(mapper.mapDataSnapshotAnimalsToAnimalsListModel(dataSnapShot)).thenReturn(listOf())

        repository?.getAllAnimals()

        verify(firebaseService).getAllAnimals()
        verify(deferredDataSnapShot).await()
        verify(mapper).mapDataSnapshotAnimalsToAnimalsListModel(dataSnapShot)
    }


    @Test
    fun getFactsByAnimalId() = runTest{
        `when`(firebaseService.getFactsByAnimalId(anyInt())).thenReturn(deferredDataSnapShot)
        `when`(deferredDataSnapShot.await()).thenReturn(dataSnapShot)
        `when`(mapper.mapFactsDataSnapshotToFactsListModel(dataSnapShot)).thenReturn(listOf())

        repository?.getFactsByAnimalId(100)

        verify(firebaseService).getFactsByAnimalId(anyInt())
        verify(deferredDataSnapShot).await()
        verify(mapper).mapFactsDataSnapshotToFactsListModel(dataSnapShot)
    }

    @Test
    fun getMediaUrl() = runTest{
        `when`(firebaseService.getMediaUrlByName(anyString())).thenReturn(deferredUri)
        `when`(deferredUri.await()).thenReturn(Uri.EMPTY)

        repository?.getMediaUrl("any string")

        verify(firebaseService).getMediaUrlByName(anyString())
        verify(deferredUri).await()
    }
}