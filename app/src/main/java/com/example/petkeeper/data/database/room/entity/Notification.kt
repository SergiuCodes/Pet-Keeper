package com.example.petkeeper.data.database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class Notification(
    var name: String,
    var scheduledDate: String,
    var petId: Int,

    @PrimaryKey(autoGenerate = true)
    var notificationId: Int? = null
)