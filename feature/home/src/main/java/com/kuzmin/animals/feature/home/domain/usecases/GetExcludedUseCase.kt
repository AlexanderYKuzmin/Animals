package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.DbRepository
import javax.inject.Inject

class GetExcludedUseCase @Inject constructor(
    private val dbRepository: DbRepository
){
    suspend operator fun invoke(name: String) = dbRepository.getExcludedIdsByName(name)
}