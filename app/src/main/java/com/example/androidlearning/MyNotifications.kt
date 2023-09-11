package com.example.androidlearning

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MyNotifications(private val context: Context) {

   private val notificationManagerCompact = NotificationManagerCompat.from(context)
    private val channelId = "my_channel_id"


    fun headsUpNotification(){
        val notificationId = 1
        val intent = Intent(context, PressNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_my_notifications)
            .setContentTitle("Heads up notification")
            .setContentText("This is the Heads up notification.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()

        notificationManagerCompact.notify(notificationId, notification)
    }

    fun expandableNotification(){
        val notificationId = 2
        val intent = Intent(context, PressNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_IMMUTABLE)

        val msg = "This is a longer description of the notification. You can provide more information here. Can use like msg/email description"
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_my_notifications)
            .setContentTitle("Expandable Notification")
            .setContentText("This is an expandable notification.")
            .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()

        notificationManagerCompact.notify(notificationId, notification)

    }


    fun actionNotification(){

        val notificationId = 3
        val intent = Intent(context, ActionBroadcastReceiver::class.java).apply {
            action = Intent.ACTION_ANSWER
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_my_notifications)
            .setContentTitle("Action Notification")
            .setContentText("This is an Action notification.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(
                R.drawable.ic_baseline_notifications_off_24, "hello",
                pendingIntent)
            .build() // not showing action icon ;(

        notificationManagerCompact.notify(notificationId, notification)

    }

    fun progressNotification(){
        val notificationId = 4

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_my_notifications)
            .setContentTitle("Progress Notification")
            .setContentText("File is getting download")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSound(null)

        val currentProgress = 0
        val maxProgress = 10000

        notificationManagerCompact.apply {
            for(i in currentProgress..maxProgress){
                // background thread should be here
                notification.setProgress(maxProgress-i,i,false)
                notify(notificationId, notification.build())
                Log.i("see",i.toString())
            }
            // loop for dublicating some downlaod task but as it is on main thread
            // for high value app is getting crash, also I have set sound to null even then sound is coming
            notification.setContentText("Download complete")
            notification.setProgress(0,0,false)
            notify(notificationId,notification.build())
        }
    }
}