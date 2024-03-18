package com.kuzmin.animals.feature.home.util

import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.home.ui.model.ParentItem

object TestUtil {
    private const val TEST_INFO = "none"
    private const val TEST_NAME_EN = "none"
    private const val TEST_NAME_RU = "нет"
    private const val TEST_SOUND_URL = "no url"

    fun createMapAnimals(): Map<AnimalType, List<Animal>> {
        return mapOf(AnimalType.BEAST to listOf(createAnimal()))
    }

    fun createAnimal(): Animal {
        return Animal(
            id = 100,
            info = TEST_INFO,
            nameEn = TEST_NAME_EN,
            nameRu = TEST_NAME_RU,
            urlSound = TEST_SOUND_URL,
            type = AnimalType.BEAST
        )
    }

    fun createParentItemList(): List<ParentItem> {
        return listOf(
            ParentItem(
                image = 100,
                title = AnimalType.BEAST,
                children = listOf(createAnimal()),
                isOpen = false
            )
        )
    }
}