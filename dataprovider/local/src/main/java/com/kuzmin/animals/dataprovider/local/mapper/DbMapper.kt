package com.kuzmin.animals.dataprovider.local.mapper

import android.util.Log
import com.kuzmin.animals.common.extension.toInt
import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.core.database.model.PhotoDb
import com.kuzmin.animals.core.database.model.TagDb
import com.kuzmin.animals.feature.api.model.AnimalType
import javax.inject.Inject

class DbMapper @Inject constructor(

) {
    fun mapAnimalPhotoToPhotoDb(animalPhoto: AnimalPhoto, isFavorite: Boolean): PhotoDb {
        with(animalPhoto) {
            return PhotoDb(
                id = photoId,
                mediumUrl = medium ?: "",
                thumbnailUrl = thumbNail ?: "",
                smallUrl = small ?: "",
                isFavorite = isFavorite.toInt(),
                animalNameEn = animalNameEn,
                animalNameRu = animalNameRu,
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
                small = smallUrl,
                animalNameEn = animalNameEn,
                animalNameRu = animalNameRu,
                description = description,
                title = title
            )
        }
    }

    fun mapPhotoDbListToAnimalPhotoList(photosDb: List<PhotoDb>): List<AnimalPhoto> {
        Log.d("Db", "Mapper: PhotoDbs to Animals")
        if (photosDb.isEmpty()) return emptyList()
        return photosDb.map { mapPhotoDbToAnimalPhoto(it) }
    }

    fun mapTypeAndTagsToTagsDb(type: AnimalType, tags: List<String>): List<TagDb> {
        if (tags.isEmpty()) return emptyList()

        val tagsDb = mutableListOf<TagDb>()
        for (i in tags.indices) {
            tagsDb.add(
                TagDb(
                    id = type.ordinal.toString() + i.toString(),
                    nameEn = tags[i],
                    animalType = type.name.lowercase()
                )
            )
        }
        return tagsDb
    }
}