package com.kuzmin.animals.feature.settings.domain.usecases

import com.kuzmin.animals.feature.api.api.DbRepository
import javax.inject.Inject

class GetTagsByTypeUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {
    suspend operator fun invoke(type: String) = dbRepository
}