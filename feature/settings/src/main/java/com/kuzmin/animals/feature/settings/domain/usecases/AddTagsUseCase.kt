package com.kuzmin.animals.feature.settings.domain.usecases

import com.kuzmin.animals.feature.api.api.DbRepository
import com.kuzmin.animals.feature.api.model.AnimalType
import javax.inject.Inject

class AddTagsUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {
    suspend operator fun invoke(type: AnimalType, tags: List<String>) = dbRepository.addTags(type, tags)
}