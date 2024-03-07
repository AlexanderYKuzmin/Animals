package com.kuzmin.animals.feature.api.model

import com.kuzmin.animals.feature.api.model.AnimalType.*

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

fun String.getEnumByRuName(): AnimalType {
    return when(this.uppercase()) {
        "ЗВЕРИ" -> BEAST
        "ПТИЦЫ" -> BIRD
        "НАСЕКОМЫЕ" -> INSECT
        "МОРСКИЕ ОБИТАТЕЛИ" -> MARINE_LIFE
        else -> {
            throw RuntimeException("Wrong Animal Type!")
        }
    }
}
