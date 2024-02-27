package com.kuzmin.animals.dataprovider.local.mapper

import com.kuzmin.animals.common.extension.toInt
import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.core.database.model.PhotoDb
import javax.inject.Inject

class DbMapper @Inject constructor(

) {
    fun mapAnimalPhotoToPhotoDb(animalPhoto: AnimalPhoto, isFavorite: Boolean): PhotoDb {
        with(animalPhoto) {
            return PhotoDb(
                id = photoId,
                mediumUrl = medium ?: "",
                thumbnailUrl = thumbNail ?: "",
                isFavorite = isFavorite.toInt(),
                animalNameEn = animalNameEn,
                description = description ?: "",
                title = title ?: ""
            )
        }
    }

    fun mapPhotoDbToAnimalPhoto(photoDb: PhotoDb): AnimalPhoto {
        with(photoDb) {
            return AnimalPhoto(
                photoId = id,
                medium = mediumUrl,
                thumbNail = thumbnailUrl,
                animalNameEn = animalNameEn,
                description = description,
                title = title
            )
        }
    }

    fun mapPhotoDbListToAnimalPhotoList(photosDb: List<PhotoDb>): List<AnimalPhoto> {
        if (photosDb.isEmpty()) return emptyList()
        return photosDb.map { mapPhotoDbToAnimalPhoto(it) }
    }
}