package com.example.androidlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.i("Abhi","Activity2 : onCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Abhi","Activity2 : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Abhi","Activity2 : onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Abhi","Activity2 : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Abhi","Activity2 : onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Abhi","Activity2 : onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Abhi","Activity2 : onDestroy")
    }
}