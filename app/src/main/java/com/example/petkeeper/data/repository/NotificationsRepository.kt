package com.example.petkeeper.data.repository

import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.PetRoomDatabase

class NotificationsRepository(private val db: PetRoomDatabase) {

    fun getAllNotifications() = db.notificationDao().getAllNotifications()

    suspend fun insertNotification(notification: Notification){
        db.notificationDao().insertNotification(notification)
    }

    suspend fun deleteNotification(notification: Notification){
        db.notificationDao().deleteNotification(notification)
    }
}