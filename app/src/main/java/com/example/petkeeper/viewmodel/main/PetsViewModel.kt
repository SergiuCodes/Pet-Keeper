package com.example.petkeeper.viewmodel.main

import androidx.lifecycle.*
import com.example.petkeeper.data.repository.PetsRepository
import com.example.petkeeper.data.database.room.entity.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetsViewModel(private val petsRepository: PetsRepository) : ViewModel() {

    val petsList: LiveData<MutableList<Pet>>? = getAllPets()

    fun insertPet(pet: Pet) = viewModelScope.launch(Dispatchers.IO) {
        petsRepository.insertPet(pet)
    }

    fun deletePet(pet:Pet) = viewModelScope.launch(Dispatchers.IO) {
        petsRepository.deletePet(pet)
    }

    fun getAllPets() = petsRepository.getAllPets()

}