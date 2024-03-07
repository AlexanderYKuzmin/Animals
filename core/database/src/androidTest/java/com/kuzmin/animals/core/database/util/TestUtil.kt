package com.kuzmin.animals.core.database.util

import com.kuzmin.animals.core.database.model.PhotoDb
import com.kuzmin.animals.core.database.model.TagDb

object TestUtil {

    fun createPhoto(id: String, isFavorite: Boolean): PhotoDb {
        return PhotoDb(
            id = id,
            mediumUrl = id + "xxx xxxx",
            thumbnailUrl = id + "xxx xxxx",
            smallUrl = id + "xxx xxxx",
            isFavorite = if (isFavorite) 1 else 0,
            animalNameEn = "butterfly",
            animalNameRu = "бабочка",
            description = "",
            title = "B"
        )
    }

    fun createTag(animalType: String, id: String): TagDb {
        return TagDb(
            id = id,
            animalType = animalType,
            nameEn = animalType + id
        )
    }
}