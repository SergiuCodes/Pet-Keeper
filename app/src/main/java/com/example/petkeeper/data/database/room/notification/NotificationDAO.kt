package com.example.petkeeper.data.database.room.notification

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petkeeper.data.database.room.entity.Notification

@Dao
interface NotificationDAO {

    @Insert
    fun insertNotification(notification: Notification)

    @Delete
    fun deleteNotification(notification: Notification)

    @Query("SELECT * FROM notifications_table ORDER BY id")
    fun getAllNotifications(): LiveData<MutableList<Notification>>?

}