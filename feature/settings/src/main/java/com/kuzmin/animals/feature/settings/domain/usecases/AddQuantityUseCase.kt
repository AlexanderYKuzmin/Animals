package com.kuzmin.animals.feature.settings.domain.usecases

import com.kuzmin.animals.feature.api.api.PrefManager
import javax.inject.Inject

class AddQuantityUseCase @Inject constructor(
    private val prefManager: PrefManager
) {
    suspend operator fun invoke(quantity: Int) = prefManager.writeData(quantity)
}