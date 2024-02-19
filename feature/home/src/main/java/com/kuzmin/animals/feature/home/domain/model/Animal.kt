package com.kuzmin.animals.feature.home.domain.model

data class Animal(
    val id: Int,

    val info: String,

    val nameEn: String,

    val nameRu: String,

    val urlSound: String,

    val type: AnimalType
)