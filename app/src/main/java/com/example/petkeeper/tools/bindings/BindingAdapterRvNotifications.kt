package com.example.petkeeper.tools.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.ui.notifications.adapter.NotificationsRvAdapter
import com.example.petkeeper.ui.pets.adapter.PetsRvAdapter

@BindingAdapter(value = ["setNotificationsList"])

fun setNotificationsListRv(
    nRecyclerView: RecyclerView,
    notificationsList: List<Notification>?
) {
    if (notificationsList != null) {
        val notificationsAdapter = NotificationsRvAdapter(nRecyclerView.context)
        nRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.adapter = notificationsAdapter
        }
        notificationsAdapter.submitList(notificationsList)
    }
}