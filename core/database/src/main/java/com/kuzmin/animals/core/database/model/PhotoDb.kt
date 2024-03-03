package com.kuzmin.animals.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    indices = [
        Index(
            value = ["medium_url", "thumbnail_url"], unique = true
        )
    ]
)
data class PhotoDb(

    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo("medium_url")
    val mediumUrl: String,

    @ColumnInfo("thumbnail_url")
    val thumbnailUrl: String,

    @ColumnInfo("small_url")
    val smallUrl: String,

    val isFavorite: Int,

    @ColumnInfo("animal_name_en")
    val animalNameEn: String,

    @ColumnInfo("animal_name_ru")
    val animalNameRu: String,

    val description: String,

    val title: String
)
