package com.example.petkeeper.viewmodel

import androidx.lifecycle.*
import com.example.petkeeper.data.repository.PetsRepository
import com.example.petkeeper.data.database.room.entity.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetsViewModel(val petsRepository: PetsRepository) : ViewModel() {

    val petsList: LiveData<List<Pet>>? = getAllPets()

    fun insertPet(pet: Pet) = viewModelScope.launch(Dispatchers.IO) {
        petsRepository.insertPet(pet)
    }

    fun deletePet() = viewModelScope.launch(Dispatchers.IO) {
        petsRepository.deletePet()
    }

    fun getAllPets() = petsRepository.getAllPets()
}