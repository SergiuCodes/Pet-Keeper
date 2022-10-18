package com.example.petkeeper.data.database.room.pet

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.database.room.entity.PetWithNotifications

@Dao
interface PetDAO {
    //Pet DAO methods
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)

    @Query("SELECT * FROM pets_table ORDER BY petId")
    fun getAllPets(): LiveData<MutableList<Pet>>?

    //Notification DAO methods
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotification(notification: Notification)

    @Delete
    suspend fun deleteNotification(notification: Notification)

    @Query("SELECT * FROM notifications_table ORDER BY notificationId")
    fun getAllNotifications(): LiveData<MutableList<Notification>>?

    //Relationship db Dao methods
    @Transaction
    @Query("SELECT * FROM pets_table where petId = :petId")
    fun getPetWithNotificationsById(petId: Int): LiveData<MutableList<PetWithNotifications>>?
}