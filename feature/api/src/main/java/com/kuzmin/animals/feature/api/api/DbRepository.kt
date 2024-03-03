package com.kuzmin.animals.feature.api.api

import com.kuzmin.animals.feature.api.model.AnimalPhoto

interface DbRepository {
    suspend fun addFavorite(animalPhoto: AnimalPhoto)

    suspend fun addExclude(animalPhoto: AnimalPhoto)

    suspend fun getAllFavorites(): List<AnimalPhoto>

    suspend fun getExcludedIdsByName(name: String): List<String>

    suspend fun getTagsByAnimalType(type: String): List<String>
}