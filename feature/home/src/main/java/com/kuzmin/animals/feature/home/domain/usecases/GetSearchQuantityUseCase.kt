package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.PrefManager
import javax.inject.Inject

class GetSearchQuantityUseCase @Inject constructor(
    private val prefManager: PrefManager
) {
    suspend operator fun invoke() = prefManager.readData()
}