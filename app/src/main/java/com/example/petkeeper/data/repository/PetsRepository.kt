package com.example.petkeeper.data.repository

import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.database.room.PetRoomDatabase

class PetsRepository(private val db: PetRoomDatabase) {

   fun getAllPets() = db.petDao().getAllPets()

    suspend fun insertPet(pet: Pet) {
        db.petDao().insertPet(pet)
    }

    suspend fun deletePet(pet: Pet){
        db.petDao().deletePet(pet)
    }
}