package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.home.api.FirebaseRepository
import javax.inject.Inject

class GetMediaUrlUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {
     suspend operator fun invoke(path: String) = repository.getMediaUrl(path)
}