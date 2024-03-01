package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.FirebaseRepository
import javax.inject.Inject

class GetAllAnimalsUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke() = firebaseRepository.getAllAnimals()
}