package com.kuzmin.animals.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "animals",
    indices = [
        Index(
            value = ["name_en", "name_ru"], unique = true
        )
    ])
data class AnimalDb (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo("name_en")
    val nameEn: String,

    @ColumnInfo("name_ru")
    val nameRu: String,

    val type: String
)