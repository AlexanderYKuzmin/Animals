package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.home.api.FirebaseRepository
import com.kuzmin.animals.feature.home.domain.model.Animal
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetAllAnimalsUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke() = firebaseRepository.getAllAnimals()
}