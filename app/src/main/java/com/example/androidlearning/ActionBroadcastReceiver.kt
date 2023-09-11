package com.example.androidlearning

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ActionBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action==Intent.ACTION_ANSWER ) {
            Log.i("Abhi", "BroadCast is working")
        }
    }
}