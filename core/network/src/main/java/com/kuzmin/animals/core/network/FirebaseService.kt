package com.kuzmin.animals.core.network

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.values
import com.kuzmin.animals.core.network.model.AnimalDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import java.lang.StringBuilder
import javax.inject.Inject


class FirebaseService @Inject constructor(
    private val fireDb: FirebaseDatabase
) {

    suspend fun getAll(): Task<DataSnapshot> {
        return fireDb.reference.get()
    }

    suspend fun getAllAnimals(): Task<DataSnapshot> {
        return fireDb.getReference(ANIMALS_REF).get()
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