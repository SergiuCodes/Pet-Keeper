package com.example.petkeeper.viewmodel.addpetscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.data.repository.PetsRepository

@Suppress("UNCHECKED_CAST")
class AddPetViewModelFactory(private val repository: PetsRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddPetViewModel(repository) as T
    }
}