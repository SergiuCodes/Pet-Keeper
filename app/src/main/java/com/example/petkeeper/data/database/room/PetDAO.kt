package com.example.petkeeper.data.database.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.petkeeper.data.database.room.entity.Pet

@Dao
interface PetDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPet(pet: Pet)

    @Query("DELETE FROM pets_table")
    suspend fun deletePet()

    @Query("SELECT * FROM pets_table ORDER BY id")
    fun getAllPets(): LiveData<MutableList<Pet>>?
}