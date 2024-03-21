package com.kuzmin.animals.feature.home.ui.model

import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType

data class ParentItem(
    val image: Int,
    val title: AnimalType,
    val children: List<Animal>,
    var isOpen: Boolean = false
)

