package com.kuzmin.animals.feature.home.api

import com.kuzmin.animals.feature.home.domain.model.Animal
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    suspend fun getAllAnimals(): List<Animal>


    suspend fun getDbTest(): String
}