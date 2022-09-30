package com.example.petkeeper.viewmodel.addpetscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.repository.PetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPetViewModel(private val petsRepository: PetsRepository): ViewModel() {

    val petsList: LiveData<MutableList<Pet>>? = getAllPets()

    fun insertPet(pet:Pet) = viewModelScope.launch ( Dispatchers.IO ) {
        petsRepository.insertPet(pet)
    }

    fun deletePet(pet:Pet) = viewModelScope.launch(Dispatchers.IO){
        petsRepository.deletePet(pet)
    }

    private fun getAllPets() = petsRepository.getAllPets()

}