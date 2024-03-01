package com.kuzmin.animals.feature.home.api

import com.kuzmin.animals.common.model.AnimalPhoto

interface DbRepository {
    suspend fun addFavorite(animalPhoto: AnimalPhoto)

    suspend fun addExclude(animalPhoto: AnimalPhoto)

    suspend fun getAllFavorites(): List<AnimalPhoto>

    suspend fun getExcludedIdsByName(name: String): List<String>
}