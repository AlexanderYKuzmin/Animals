package com.kuzmin.animals.core.network

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred
import java.lang.StringBuilder
import javax.inject.Inject


class FirebaseService @Inject constructor(
    private val fireDb: FirebaseDatabase,
    //private val fireStorage: FirebaseStorage
    private val storageRef: StorageReference
) {

    fun getAll(): Task<DataSnapshot> {
        return fireDb.reference.get()
    }

    fun getAllAnimals(): Deferred<DataSnapshot> {
        Log.d("Db", "FireBase service: getAllAnimals")

        return fireDb.getReference(ANIMALS_REF).get().addOnSuccessListener {
            Log.d("Db", "FirebaseService DataSnapshot: $it")
        }.addOnFailureListener {
            Log.d("Db", "FireBase service: failure, e: $it")
        }.asDeferred()
    }

    fun getFactsByAnimalId(id: Int): Deferred<DataSnapshot> {
        Log.d("Db", "FireBase service: getFacts")
        Log.d("Db", "FireBase service: id = $id")
        Log.d("Db", "FireBase service: id string = ${id.toString()}")
        return fireDb.getReference(FACTS_REF).child(id.toString()).get().addOnSuccessListener {
            Log.d("Db", "FirebaseService DataSnapshot get facts: $it")
        }.addOnFailureListener {
            Log.d("Db", "FireBase service: failure get facts, e: $it")
        }.asDeferred()
    }

    fun getMediaUrlByName(path: String): Deferred<Uri> {
        return storageRef.child(path).downloadUrl.addOnSuccessListener {
            // Got the download URL for 'users/me/profile.png'
        }.addOnFailureListener {
            // Handle any errors
        }.asDeferred()
    }


    suspend fun test(): String {
        Log.d("Db", "Service test started.")
        val string2 = StringBuilder()
        //val string1 = fireDb.getReference(ANIMALS_REF).child("badger").key.toString()

        /*fireDb.reference.get().addOnSuccessListener { obj ->
            obj.children.forEach {
                Log.d("Db", "${it.key}")
            }
        }.addOnFailureListener{
            Log.e("Db", "Error getting data", it)
        }*/
        /*fireDb.getReference(ANIMALS_REF).child("mouse").addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val text = snapshot.getValue(AnimalDto::class.java)?.info
                    Log.d("Db", "TEXT: $text")
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )*/

        fireDb.getReference(ANIMALS_REF).child("mouse").child("info").get().addOnSuccessListener {
            Log.d("Db", "Mouse info: ${it.value}")
        }

        return "Ok"
    }

    companion object {
        const val ANIMALS_REF = "animals"
        const val FACTS_REF = "facts"
    }
}