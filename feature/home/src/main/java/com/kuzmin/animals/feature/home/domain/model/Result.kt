package com.kuzmin.animals.feature.home.domain.model

sealed class Result {
    data object Loading : Result()
    class Success(val animals: Map<AnimalType, List<Animal>>): Result()
    class Error(val throwable: Throwable): Result()
}