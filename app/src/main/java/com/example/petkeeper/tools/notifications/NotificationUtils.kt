package com.example.petkeeper.tools.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.petkeeper.R

const val CHANNEL_ID = "pet_notifications_id"
const val NOTIFICATION_ID = 0
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class NotificationUtils : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent?.getStringExtra(titleExtra))
            .setContentText(intent?.getStringExtra(messageExtra))
            .build()

        val notificationsManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationsManager.notify(NOTIFICATION_ID, notification)
    }
}
