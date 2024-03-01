package com.kuzmin.animals.feature.home.domain.model

sealed class Result {
    data object Loading : Result()
    class Success(val animals: Map<com.kuzmin.animals.feature.api.model.AnimalType, List<com.kuzmin.animals.feature.api.model.Animal>>): Result()
    class Error(val throwable: Throwable): Result()
}