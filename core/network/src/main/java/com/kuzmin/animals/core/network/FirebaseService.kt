package com.kuzmin.animals.core.network

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred
import javax.inject.Inject


class FirebaseService @Inject constructor(
    private val fireDb: FirebaseDatabase,
    private val storageRef: StorageReference
) {

    fun getAllAnimals(): Deferred<DataSnapshot> {
        return fireDb.getReference(ANIMALS_REF).get().asDeferred()
    }

    fun getFactsByAnimalId(id: Int): Deferred<DataSnapshot> {
        return fireDb.getReference(FACTS_REF).child(id.toString()).get().asDeferred()
    }

    fun getMediaUrlByName(path: String): Deferred<Uri> {
        return storageRef.child(path).downloadUrl.asDeferred()
    }

    companion object {
        const val ANIMALS_REF = "animals"
        const val FACTS_REF = "facts"
    }
}