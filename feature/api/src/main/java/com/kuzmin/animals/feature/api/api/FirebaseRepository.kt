package com.kuzmin.animals.feature.api.api

import android.net.Uri

interface FirebaseRepository {

    suspend fun getAllAnimals(): List<com.kuzmin.animals.feature.api.model.Animal>

    suspend fun getFactsByAnimalId(id: Int): List<com.kuzmin.animals.feature.api.model.Fact>

    suspend fun getMediaUrl(path: String): Uri
}