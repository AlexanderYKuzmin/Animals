package com.kuzmin.animals.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tags",
    primaryKeys = ["name_en", "animal_type"])
data class TagDb(

    @ColumnInfo("name_en")
    val nameEn: String,

    @ColumnInfo("animal_type")
    val animalType: String
)
