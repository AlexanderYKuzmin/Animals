package com.kuzmin.animals.feature.home.ui.model

import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.api.model.AnimalType.*
import com.kuzmin.animals.feature.home.R
import javax.inject.Inject

class ParentItemFactory @Inject constructor(

) {
    fun createParentItem(title: AnimalType, children: List<Animal>) =
        ParentItem(
            image = getImage(title),
            title = title,
            children = children,
        )

    private fun getImage(title: AnimalType): Int {
        return when(title) {
            BEAST -> R.drawable.squirrel_2
            BIRD -> R.drawable.crow_2
            INSECT -> R.drawable.bug_4
            MARINE_LIFE -> R.drawable.fish_4
            else -> R.drawable.bug_4
        }
    }
}