package com.kuzmin.animals.feature.home.domain.model

import com.kuzmin.animals.feature.home.domain.model.AnimalType.*

enum class AnimalType(s: String) {
    BEAST("beast"),
    BIRD("bird"),
    INSECT("insect"),
    MARINE_LIFE("marine_life"),
    NO_TYPE("no_type")
}
fun AnimalType.getNameRu(): String {
    return when(this) {
        BEAST -> "Звери"
        BIRD -> "Птицы"
        INSECT -> "Насекомые"
        MARINE_LIFE -> "Морские обитатели"
        else -> "Без типа"
    }
}