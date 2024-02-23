package com.kuzmin.animals.dataprovider.remote.model

import com.google.firebase.database.PropertyName

data class FactDto(
    @get:PropertyName("animal_id")
    @set:PropertyName("animal_id")
    var animalId: Int = 0,

    @set:PropertyName("text")
    var text: String = "No text"
)
