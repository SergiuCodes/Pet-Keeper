package com.example.petkeeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.data.repository.PetsRepository

@Suppress("UNCHECKED_CAST")
class PetViewModelFactory(private val repository: PetsRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetsViewModel(repository) as T
    }
}