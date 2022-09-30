package com.example.petkeeper.data.database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class Notification(
    var name: String,
    var species: String,
    var scheduledDate: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)