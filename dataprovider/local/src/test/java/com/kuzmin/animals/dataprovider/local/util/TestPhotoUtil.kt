package com.kuzmin.animals.dataprovider.local.util

import com.kuzmin.animals.dataprovider.local.util.TestConstants.ANIMAL_NAME_EN
import com.kuzmin.animals.dataprovider.local.util.TestConstants.ANIMAL_NAME_RU
import com.kuzmin.animals.dataprovider.local.util.TestConstants.DESCRIPTION_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.ID_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.MEDIUM_URL_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.SMALL_URL_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.THUMBNAIL_URL_TEST
import com.kuzmin.animals.dataprovider.local.util.TestConstants.TITLE_TEST
import com.kuzmin.animals.feature.api.model.AnimalPhoto

object TestPhotoUtil {
    fun createAnimalPhotoTestCommon(): AnimalPhoto {
        return AnimalPhoto(
            photoId = ID_TEST,
            title = TITLE_TEST,
            description = DESCRIPTION_TEST,
            thumbNail = THUMBNAIL_URL_TEST,
            medium = MEDIUM_URL_TEST,
            small = SMALL_URL_TEST,
            animalNameEn = ANIMAL_NAME_EN,
            animalNameRu = ANIMAL_NAME_RU
        )
    }

    fun createAnimalPhotoTestWithNullFields(): AnimalPhoto {
        return AnimalPhoto(
            photoId = ID_TEST,
            title = null ,
            description = null,
            thumbNail = null,
            medium = null,
            small = null,
            animalNameEn = ANIMAL_NAME_EN,
            animalNameRu = ANIMAL_NAME_RU
        )
    }
}