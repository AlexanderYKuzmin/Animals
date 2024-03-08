package com.kuzmin.animals.feature.home.domain.model

import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType

sealed class Result {
    data object Loading : Result()
    class Success(val animals: Map<AnimalType, List<Animal>>): Result()
    class Error(val throwable: Throwable): Result()
}