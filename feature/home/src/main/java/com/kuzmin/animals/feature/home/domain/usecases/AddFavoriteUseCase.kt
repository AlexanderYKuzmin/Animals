package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.home.api.DbRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {
    suspend operator fun invoke(animalPhoto: AnimalPhoto) = dbRepository.addFavorite(animalPhoto)
}