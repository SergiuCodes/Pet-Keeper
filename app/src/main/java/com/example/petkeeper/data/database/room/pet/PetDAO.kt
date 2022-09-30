package com.example.petkeeper.data.database.room.pet

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.petkeeper.data.database.room.entity.Pet

@Dao
interface PetDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)

    @Query("SELECT * FROM pets_table ORDER BY id")
    fun getAllPets(): LiveData<MutableList<Pet>>?
}