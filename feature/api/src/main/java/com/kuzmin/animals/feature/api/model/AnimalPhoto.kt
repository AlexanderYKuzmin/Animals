package com.kuzmin.animals.feature.api.model

data class AnimalPhoto(
    val photoId: String,

    val title: String?,

    val description: String?,

    val thumbNail: String?,

    val medium: String?,

    val small: String?,

    val animalNameEn: String,

    val animalNameRu: String
)
