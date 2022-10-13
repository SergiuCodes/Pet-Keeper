package com.example.petkeeper.data.database.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PetWithNotifications(
    @Embedded val pet: Pet,
    @Relation(
        parentColumn = "petId",
        entityColumn = "petId"
    )
    val notifications: List<Notification>
)