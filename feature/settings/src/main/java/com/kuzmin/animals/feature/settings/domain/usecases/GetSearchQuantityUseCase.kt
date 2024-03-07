package com.kuzmin.animals.feature.settings.domain.usecases

import com.kuzmin.animals.feature.api.api.PrefManager
import com.kuzmin.animals.feature.api.basics.BaseGetSearchQuantityByTypeUseCase
import javax.inject.Inject

class GetSearchQuantityUseCase @Inject constructor(
    prefManager: PrefManager
) : BaseGetSearchQuantityByTypeUseCase(prefManager)