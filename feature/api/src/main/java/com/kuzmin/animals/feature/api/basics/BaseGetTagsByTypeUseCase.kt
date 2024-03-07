package com.kuzmin.animals.feature.api.basics

import com.kuzmin.animals.feature.api.api.DbRepository

abstract class BaseGetTagsByTypeUseCase(
    private val dbRepository: DbRepository
) {
    suspend operator fun invoke(type: String) = dbRepository.getTagsByAnimalType(type)
}