package com.example.petkeeper.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.database.room.pet.PetDAO
import com.example.petkeeper.tools.Converter

@Database(entities = [Pet::class, Notification::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
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
