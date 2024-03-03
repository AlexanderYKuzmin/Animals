package com.kuzmin.animals.dataprovider.local

import com.kuzmin.animals.core.database.AnimalDao
import com.kuzmin.animals.dataprovider.local.mapper.DbMapper
import com.kuzmin.animals.feature.api.api.DbRepository
import com.kuzmin.animals.feature.api.model.AnimalPhoto
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

    override suspend fun getAllFavorites(): List<AnimalPhoto> {
        return mapper.mapPhotoDbListToAnimalPhotoList(animalDao.getFavorites())
    }

    override suspend fun getExcludedIdsByName(name: String): List<String> {
        return animalDao.getBlackListIdsByName(name)
    }

    override suspend fun getTagsByAnimalType(type: String): List<String> {
        return animalDao.getTagsByAnimalType(type)
    }
}