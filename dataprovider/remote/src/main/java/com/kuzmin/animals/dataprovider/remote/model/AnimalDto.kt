package com.kuzmin.animals.dataprovider.remote.model

import com.google.firebase.database.PropertyName


data class AnimalDto(
    val id: Int = 0,

    @get:PropertyName("info")
    @set:PropertyName("info")
    var info: String = "None",

    @get:PropertyName("name_en")
    @set:PropertyName("name_en")
    var nameEn: String = "None",

    @get:PropertyName("name_ru")
    @set:PropertyName("name_ru")
    var nameRu: String = "Нет",

    @get:PropertyName("type")
    @set:PropertyName("type")
    var type: String = "no_type",

    @get:PropertyName("url_sound")
    @set:PropertyName("url_sound")
    var urlSound: String = "no sounds"
)
