package com.kuzmin.animals.feature.favorite.domain.usecases

import com.kuzmin.animals.feature.home.api.DbRepository
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repository: DbRepository
) {
    suspend operator fun invoke() = repository.getAllFavorites()
}