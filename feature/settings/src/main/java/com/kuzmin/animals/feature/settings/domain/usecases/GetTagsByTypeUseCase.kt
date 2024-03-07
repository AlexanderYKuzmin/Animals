package com.kuzmin.animals.feature.settings.domain.usecases

import com.kuzmin.animals.feature.api.api.DbRepository
import com.kuzmin.animals.feature.api.basics.BaseGetTagsByTypeUseCase
import javax.inject.Inject

class GetTagsByTypeUseCase @Inject constructor(
    dbRepository: DbRepository
) : BaseGetTagsByTypeUseCase(dbRepository)