package com.example.petkeeper.viewmodel.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationsViewModel(private val repository: NotificationsRepository): ViewModel() {

    val notificationsList: LiveData<MutableList<Notification>>? = getAllNotifications()

    fun insertNotification(notification: Notification) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNotification(notification)
    }

    fun deleteNotification(notification: Notification) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNotification(notification)
    }

    private fun getAllNotifications() = repository.getAllNotifications()
}