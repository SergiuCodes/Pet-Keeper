package com.example.petkeeper.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.repository.PetsRepository

class PetsViewModel(private val petsRepository: PetsRepository) : ViewModel() {

    val petsList: LiveData<MutableList<Pet>>? = getAllPets()

    fun getAllPets() = petsRepository.getAllPets()

}