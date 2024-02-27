package com.kuzmin.animals.dataprovider.local

import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.core.database.AnimalDao
import com.kuzmin.animals.dataprovider.local.mapper.DbMapper
import com.kuzmin.animals.feature.home.api.DbRepository
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(
    private val animalDao: AnimalDao,
    private val mapper: DbMapper
) : DbRepository {
    override suspend fun addFavorite(animalPhoto: AnimalPhoto) {
        animalDao.addPhoto(mapper.mapAnimalPhotoToPhotoDb(animalPhoto, true))
    }

    override suspend fun addExclude(animalPhoto: AnimalPhoto) {
        animalDao.addPhoto(mapper.mapAnimalPhotoToPhotoDb(animalPhoto, false))
    }
}