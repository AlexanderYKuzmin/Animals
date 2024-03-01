package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.home.api.DbRepository
import javax.inject.Inject

class GetExcludedUseCase @Inject constructor(
    private val dbRepository: DbRepository
){
    suspend fun getExcludedIdsByName(name: String) = dbRepository.getExcludedIdsByName(name)
}