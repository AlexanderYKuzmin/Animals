package com.kuzmin.animals.dataprovider.local.util

import com.kuzmin.animals.core.database.model.PhotoDb
import com.kuzmin.animals.dataprovider.local.util.TestConstants.ANIMAL_NAME_EN
import com.kuzmin.animals.dataprovider.local.util.TestConstants.ANIMAL_NAME_RU
import com.kuzmin.animals.dataprovider.local.util.TestConstants.DESCRIPTION_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.ID_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.MEDIUM_URL_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.SMALL_URL_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.THUMBNAIL_URL_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.TITLE_TEST

object TestPhotoDbUtil {
    fun createAnimalPhotoDbFavorite(): PhotoDb {
        return PhotoDb(
            id = ID_TEST,
            mediumUrl = MEDIUM_URL_TEST,
            thumbnailUrl = THUMBNAIL_URL_TEST,
            smallUrl = SMALL_URL_TEST,
            isFavorite = 1,
            animalNameEn = ANIMAL_NAME_EN,
            animalNameRu = ANIMAL_NAME_RU,
            description = DESCRIPTION_TEST,
            title = TITLE_TEST
        )
    }

    fun createAnimalPhotoDbExcluded(): PhotoDb {
        return PhotoDb(
            id = ID_TEST,
            mediumUrl = MEDIUM_URL_TEST,
            thumbnailUrl = THUMBNAIL_URL_TEST,
            smallUrl = SMALL_URL_TEST,
            isFavorite = 0,
            animalNameEn = ANIMAL_NAME_EN,
            animalNameRu = ANIMAL_NAME_RU,
            description = DESCRIPTION_TEST,
            title = TITLE_TEST
        )
    }

    fun createAnimalPhotoDbFavoriteForPhotoWithNullFields(): PhotoDb {
        return PhotoDb(
            id = ID_TEST,
            mediumUrl = "",
            thumbnailUrl = "",
            smallUrl = "",
            isFavorite = 1,
            animalNameEn = ANIMAL_NAME_EN,
            animalNameRu = ANIMAL_NAME_RU,
            description = "",
            title = ""
        )
    }
}