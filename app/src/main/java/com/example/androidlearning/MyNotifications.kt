package com.example.androidlearning

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MyNotifications(private val context: Context) {

    val notificationManagerCompact = NotificationManagerCompat.from(context)


    fun simpleNotification(){
        val intent = Intent(context,)
        val channelId = "my_channel_id"
        val notificationId = 1 // You can use any unique integer for the notification ID

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(androidx.loader.R.drawable.notification_bg)
            .setContentTitle("My Notification Title")
            .setContentText("This is the content of the notification.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManagerCompact.notify(notificationId, notification)
    }
}