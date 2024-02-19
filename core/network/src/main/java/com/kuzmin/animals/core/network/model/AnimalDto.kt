package com.kuzmin.animals.core.network.model

import com.google.firebase.database.PropertyName


data class AnimalDto(
    val id: Int = 0,

    @PropertyName("fact")
    val info: String = "None",

    @PropertyName("name_en")
    val nameEn: String = "None",

    @PropertyName("name_ru")
    val nameRu: String = "Нет",

    @PropertyName("type")
    val type: String = "No type",

    @PropertyName("url_sound")
    val urlSound: String = "no sounds"
)
