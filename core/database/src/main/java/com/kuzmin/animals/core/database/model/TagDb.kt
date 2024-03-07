package com.kuzmin.animals.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tags"
)
data class TagDb(
    @PrimaryKey(false)
    val id: String,

    @ColumnInfo("name_en")
    val nameEn: String,

    @ColumnInfo("animal_type")
    val animalType: String
)
