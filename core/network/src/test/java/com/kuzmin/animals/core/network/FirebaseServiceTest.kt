package com.kuzmin.animals.core.network

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FirebaseServiceTest {
    private var firebaseService: FirebaseService? = null

    @Mock
    private lateinit var firebaseDatabase: FirebaseDatabase

    @Mock
    private lateinit var storageReference: StorageReference

    @Mock
    private lateinit var databaseReference: DatabaseReference

    @Mock
    private lateinit var taskUri: Task<Uri>

    @Mock
    private lateinit var successfulTask: Task<DataSnapshot>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        firebaseService = FirebaseService(firebaseDatabase, storageReference)
    }

    @Test
    fun getAllAnimals() {
        whenever(firebaseDatabase.getReference(any())).thenReturn(databaseReference)
        whenever(databaseReference.get()).thenReturn(successfulTask)

        firebaseService?.getAllAnimals()

        verify(firebaseDatabase).getReference(anyString())
        verify(databaseReference).get()
    }

    @Test
    fun getFactsByAnimalId() {
        whenever(firebaseDatabase.getReference(anyString())).thenReturn(databaseReference)
        whenever(databaseReference.child(TEST_ID.toString())).thenReturn(databaseReference)
        whenever(databaseReference.get()).thenReturn(successfulTask)

        firebaseService?.getFactsByAnimalId(TEST_ID)

        verify(firebaseDatabase).getReference(anyString())
        verify(databaseReference).child(TEST_ID.toString())
        verify(databaseReference).get()
    }

    @Test
    fun getMediaUrlByName() {
        whenever(storageReference.child(anyString())).thenReturn(storageReference)
        whenever(storageReference.downloadUrl).thenReturn(taskUri)

        firebaseService?.getMediaUrlByName(PATH_TEST)

        verify(storageReference).child(anyString())
        verify(storageReference).downloadUrl
    }

    @After
    fun tearDown() {
        firebaseService = null
    }

    companion object {
        private const val TEST_ID = 1000
        private const val PATH_TEST = "path test"
    }
}