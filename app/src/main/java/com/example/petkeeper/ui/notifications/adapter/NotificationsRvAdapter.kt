package com.example.petkeeper.ui.notifications.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.entity.Notification

class NotificationsRvAdapter(private val context: Context, private var notificationsList: List<Notification> = ArrayList()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mRowBinding: ViewDataBinding

    fun submitList(list: List<Notification>) {
        notificationsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        mRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.notification_item,
            parent,
            false
        )
        return NotificationViewHolder(mRowBinding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val notificationItem = notificationsList[position]
        (holder as NotificationViewHolder).bind(notificationItem)
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }
}