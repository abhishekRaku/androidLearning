package com.example.androidlearning

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ContentInfoCompat.Flags


class MyNotifications(private val context: Context) {

    val notificationManagerCompact = NotificationManagerCompat.from(context)


    fun simpleNotification(){
        val notificationId = 1
        val intent = Intent(context, PressNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_IMMUTABLE)
        val channelId = "my_channel_id"

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(androidx.loader.R.drawable.notification_bg)
            .setContentTitle("My Notification Title")
            .setContentText("This is the content of the notification.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        notificationManagerCompact.notify(notificationId, notification)
    }
}