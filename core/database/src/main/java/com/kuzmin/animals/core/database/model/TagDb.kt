package com.kuzmin.animals.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["name_en", "animal_type_name_en"])
data class TagDb(

    @ColumnInfo("name_en")
    val nameEn: String,

    @ColumnInfo("animal_type_name_en")
    val animalTypeNameEn: String
)
