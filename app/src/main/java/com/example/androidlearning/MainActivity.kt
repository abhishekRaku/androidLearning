package com.example.androidlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var goToBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Abhi","Activity1: onCreate")

        goToBtn = findViewById(R.id.goTobtn)

        goToBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Abhi","Activity1 : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Abhi","Activity1 : onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Abhi","Activity1 : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Abhi","Activity1 : onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Abhi","Activity1 : onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Abhi","Activity1 : onDestroy")
    }
}