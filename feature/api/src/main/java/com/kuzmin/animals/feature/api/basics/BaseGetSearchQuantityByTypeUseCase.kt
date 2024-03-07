package com.kuzmin.animals.feature.api.basics

import com.kuzmin.animals.feature.api.api.PrefManager

abstract class BaseGetSearchQuantityByTypeUseCase(
    private val prefManager: PrefManager
) {
    suspend operator fun invoke() = prefManager.readData()
}