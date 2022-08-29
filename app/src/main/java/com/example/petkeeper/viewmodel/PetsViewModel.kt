package com.example.petkeeper.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.repository.PetsRepository
import com.example.petkeeper.data.database.room.entity.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetsViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    var petsList: LiveData<List<Pet>>?
    val petsRepository: PetsRepository

    init {
        val petDao = PetRoomDatabase.getDatabaseInstance(application)?.petDao()
        petsRepository = PetsRepository(petDao)
        petsList = petsRepository.getAllPets()
    }

    fun insertPet(pet: Pet) = viewModelScope.launch(Dispatchers.IO) {
        petsRepository.insertPet(pet)
    }

    fun deletePet() = viewModelScope.launch(Dispatchers.IO) {
        petsRepository.deletePet()
    }

    fun getAllPets() = petsRepository.getAllPets()
}