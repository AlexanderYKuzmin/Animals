package com.kuzmin.animals.dataprovider.local.util

import com.kuzmin.animals.core.database.model.TagDb

object TestTagUtil {
    fun getTagDbList(): List<TagDb> {
        return listOf(
            TagDb(
               id = "20",
               nameEn = "wild",
               animalType = "insect"
            ),
            TagDb(
                id = "21",
                nameEn = "nature",
                animalType = "insect"
            ),
        )
    }
}