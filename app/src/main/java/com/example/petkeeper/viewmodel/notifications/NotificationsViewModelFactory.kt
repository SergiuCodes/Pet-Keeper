package com.example.petkeeper.viewmodel.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.data.repository.NotificationsRepository

@Suppress("UNCHECKED_CAST")
class NotificationsViewModelFactory(private val repository: NotificationsRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationsViewModel(repository) as T
    }
}