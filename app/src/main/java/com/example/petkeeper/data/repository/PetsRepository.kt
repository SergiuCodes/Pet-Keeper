package com.example.petkeeper.data.repository

import com.example.petkeeper.data.database.room.PetDAO
import com.example.petkeeper.data.database.room.entity.Pet

class PetsRepository(private val petDAO: PetDAO?) {

   fun getAllPets() = petDAO?.getAllPets()

    suspend fun insertPet(pet: Pet) {
        petDAO?.insertPet(pet)
    }

    suspend fun deletePet(){
        petDAO?.deletePet()
    }
}