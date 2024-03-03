package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.DbRepository
import javax.inject.Inject

class GetTagsByAnimalTypeUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {
    suspend operator fun invoke(type: String) = dbRepository.getTagsByAnimalType(type)
}