package com.kuzmin.animals.feature.favorite.domain.model

import com.kuzmin.animals.feature.api.model.AnimalPhoto

sealed class FavoritesResult {
    data object Loading : FavoritesResult()
    class Success(val favoriteList: List<AnimalPhoto>): FavoritesResult()
    class Error(val throwable: Throwable): FavoritesResult()
}
