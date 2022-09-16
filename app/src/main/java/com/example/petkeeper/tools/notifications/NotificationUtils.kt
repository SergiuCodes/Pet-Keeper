package com.example.petkeeper.tools.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.petkeeper.R
import com.example.petkeeper.tools.Constants

class NotificationUtils : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notification: Notification = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent?.getStringExtra(Constants.titleExtra))
            .setContentText(intent?.getStringExtra(Constants.messageExtra))
            .build()

        val notificationsManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationsManager.notify(Constants.NOTIFICATION_ID, notification)
    }
}
