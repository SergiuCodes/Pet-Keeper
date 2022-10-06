package com.example.petkeeper.ui.notifications.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.databinding.NotificationItemBinding

class NotificationViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(notification: Notification) {
        val binding: NotificationItemBinding? = DataBindingUtil.getBinding(view)

        if(binding != null) {
            binding.notification = notification
            binding.executePendingBindings()
        }
    }
}