package com.kuzmin.animals.dataprovider.remote.util

import com.kuzmin.animals.dataprovider.remote.model.AnimalDto
import com.kuzmin.animals.dataprovider.remote.model.FactDto
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.api.model.Fact

object TestUtil {

    private const val TEST_INFO = "none"
    private const val TEST_NAME_EN = "none"
    private const val TEST_NAME_RU = "нет"
    private const val TEST_SOUND_URL = "no url"
    private const val TEST_TYPE_NAME = "no_type"
    private const val TEST_TEXT = "text"

    fun createAnimalDto(): AnimalDto {
        return AnimalDto(
            id = 100,
            info = TEST_INFO,
            nameEn = TEST_NAME_EN,
            nameRu = TEST_NAME_RU,
            type = TEST_TYPE_NAME,
            urlSound = TEST_SOUND_URL
        )
    }

    fun createAnimal(): Animal {
        return Animal(
            id = 100,
            info = TEST_INFO,
            nameEn = TEST_NAME_EN,
            nameRu = TEST_NAME_RU,
            urlSound = TEST_SOUND_URL,
            type = AnimalType.NO_TYPE
        )
    }

    fun createFactDto(): FactDto {
        return FactDto(
            animalId = 100,
            text = TEST_TEXT
        )
    }

    fun createFact(): Fact {
        return Fact(
            animalId = 100,
            fact = TEST_TEXT
        )
    }
}