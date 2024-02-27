package com.kuzmin.animals.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kuzmin.animals.core.database.model.PhotoDb

@Database(
    entities = [
        PhotoDb::class
    ],
    version = 2
)
abstract class AnimalDatabase : RoomDatabase(){
    companion object {

        private var db: AnimalDatabase? = null
        private const val DB_NAME = "local_animal.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AnimalDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AnimalDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun tmDao(): AnimalDao
}