package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.home.api.DbRepository
import javax.inject.Inject

class AddExcludeUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {
    suspend operator fun invoke(photo: AnimalPhoto) = dbRepository.addExclude(photo)
}