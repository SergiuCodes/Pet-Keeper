package com.example.petkeeper.data.repository

import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.PetRoomDatabase

class NotificationsRepository(private val db: PetRoomDatabase) {

    fun getAllNotifications() = db.petDao().getAllNotifications()

    suspend fun insertNotification(notification: Notification){
        db.petDao().insertNotification(notification)
    }

    suspend fun deleteNotification(notification: Notification){
        db.petDao().deleteNotification(notification)
    }
}