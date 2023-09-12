package com.example.androidlearning

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat.getSystemService


class ActionBroadcastReceiver: BroadcastReceiver() {
    private val KEY_TEXT_REPLY = "key_text_reply"

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        if(intent?.action==Intent.ACTION_ANSWER ) {
            Log.i("Abhi", "BroadCast is working")
//            notificationManager!!.cancel(3)
            val myIntent = Intent(context,PressNotification::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            pendingIntent.send()
        }


        if(intent?.action=="com.example.androidlearning.REPLY"){
            Log.i("Abhi", RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_TEXT_REPLY).toString())
        }

    }
}