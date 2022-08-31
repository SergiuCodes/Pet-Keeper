package com.example.petkeeper.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petkeeper.data.database.room.entity.Pet

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetRoomDatabase : RoomDatabase() {

    abstract fun petDao(): PetDAO

    companion object {
        @Volatile
        private var instance: PetRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: getDatabaseInstance(context).also { instance = it }
        }

        private fun getDatabaseInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PetRoomDatabase::class.java,
                "petsDatabase"
            )
                .allowMainThreadQueries()
                .build()
    }
}
