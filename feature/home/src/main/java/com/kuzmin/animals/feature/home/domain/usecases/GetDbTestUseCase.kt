package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.FirebaseRepository
import javax.inject.Inject

class GetDbTestUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke() = repository.getDbTest()
}