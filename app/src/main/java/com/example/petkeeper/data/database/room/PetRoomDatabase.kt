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
        private var INSTANCE: PetRoomDatabase? = null

        fun getDatabaseInstance(context: Context): PetRoomDatabase? {
            synchronized(this) {
                var instance = INSTANCE
                if (instance != null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PetRoomDatabase::class.java,
                        "petsDatabase"
                    )
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}