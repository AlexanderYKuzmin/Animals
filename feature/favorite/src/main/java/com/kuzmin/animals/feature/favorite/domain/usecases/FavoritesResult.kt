package com.kuzmin.animals.feature.favorite.domain.usecases

import com.kuzmin.animals.common.model.AnimalPhoto

sealed class FavoritesResult() {
    data object Loading : FavoritesResult()
    class Success(val favoriteList: List<AnimalPhoto>): FavoritesResult()
    class Error(val throwable: Throwable): FavoritesResult()
}
