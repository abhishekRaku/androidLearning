package com.example.androidlearning

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var goToBtn: Button
    private lateinit var tvCount: TextView
    private lateinit var btnCount: Button
    private var count: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Abhi","Activity1: onCreate")

        goToBtn = findViewById(R.id.goTobtn)
        tvCount = findViewById(R.id.tvCount)
        btnCount = findViewById(R.id.btnCount)

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

        btnCount.setOnClickListener {
            count++
            tvCount.setText(count.toString())
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("countInt",count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedCount = savedInstanceState.getInt("countInt")
        count = savedCount
        tvCount.text = savedCount.toString()
    }


}