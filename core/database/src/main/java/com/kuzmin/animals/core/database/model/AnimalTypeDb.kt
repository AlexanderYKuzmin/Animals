package com.kuzmin.animals.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimalTypeDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("type_en")
    val typeEn: String,

    @ColumnInfo("type_ru")
    val typeRu: String,

    @ColumnInfo("photo_quantity")
    val photoQuantity: Int
)
