package com.kuzmin.animals.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuzmin.animals.core.database.model.PhotoDb
import com.kuzmin.animals.core.database.model.TagDb
import java.util.Objects

@Dao
interface AnimalDao {

    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos(): List<PhotoDb>

    @Query("SELECT id FROM photos")
    suspend fun getAllIds(): List<String>

    @Query("SELECT * FROM photos WHERE isFavorite = 1")
    suspend fun getFavorites(): List<PhotoDb>

    @Query("SELECT id FROM photos WHERE isFavorite = 1")
    suspend fun getFavoritesIds(): List<String>

    @Query("SELECT * FROM photos WHERE isFavorite = 0")
    suspend fun getBlackList(): List<PhotoDb>

    @Query("SELECT id FROM photos WHERE isFavorite = 0")
    suspend fun getBlackListIds(): List<String>

    @Query("SELECT * FROM photos WHERE animal_name_en LIKE :name AND isFavorite = 1")
    suspend fun getFavoritesByName(name: String): List<PhotoDb>

    @Query("SELECT * FROM photos WHERE animal_name_en LIKE :name AND isFavorite = 0")
    suspend fun getBlackListByName(name: String): List<PhotoDb>

    @Query("SELECT id FROM photos WHERE animal_name_en LIKE :name AND isFavorite = 1")
    suspend fun getFavoriteIdsByName(name: String): List<String>

    @Query("SELECT id FROM photos WHERE animal_name_en LIKE :name AND isFavorite = 0")
    suspend fun getBlackListIdsByName(name: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhoto(photoDb: PhotoDb)

    @Query("DELETE FROM photos WHERE id = :id")
    suspend fun deletePhoto(id: String): Int

    @Query("SELECT name_en FROM tags WHERE animal_type LIKE :type")
    suspend fun getTagsByAnimalType(type: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTags(tags: List<TagDb>)
}