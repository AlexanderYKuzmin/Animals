package com.kuzmin.animals.feature.home.api

import android.net.Uri
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.Fact
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    suspend fun getAllAnimals(): List<Animal>

    suspend fun getFactsByAnimalId(id: Int): List<Fact>

    suspend fun getMediaUrl(path: String): Uri

    //suspend fun getAllFacts():

    suspend fun getDbTest(): String
}