package com.kuzmin.animals.feature.home.domain.usecases

import com.kuzmin.animals.feature.api.api.DbRepository
import com.kuzmin.animals.feature.api.basics.BaseGetTagsByTypeUseCase
import javax.inject.Inject

class GetTagsByAnimalTypeUseCase @Inject constructor(
    dbRepository: DbRepository
) : BaseGetTagsByTypeUseCase(dbRepository)