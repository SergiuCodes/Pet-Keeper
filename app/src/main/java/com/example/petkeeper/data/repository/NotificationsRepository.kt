package com.example.petkeeper.data.repository

import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.database.room.entity.Pet

class NotificationsRepository(private val db: PetRoomDatabase) {

    fun getAllNotifications() = db.petDao().getAllNotifications()

    fun getAllNotificationsById(pet: Pet) = pet.petId?.let {
        db.petDao().getPetWithNotificationsById(
            it
        )
    }

    suspend fun insertNotification(notification: Notification){
        db.petDao().insertNotification(notification)
    }

    suspend fun deleteNotification(notification: Notification){
        db.petDao().deleteNotification(notification)
    }
}