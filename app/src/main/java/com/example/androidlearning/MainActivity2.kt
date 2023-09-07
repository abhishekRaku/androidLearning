package com.example.androidlearning

import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearning.databinding.ActivityMain2Binding
import com.example.androidlearning.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val givenData = intent.getStringExtra("GIVEN_DATA").toString()
        if(givenData.isNotEmpty()){
            binding.tvResult.text = "WELCOME $givenData"
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnReturn.setOnClickListener {
            if(binding.etvData.text.toString().isNotEmpty()){
                val intent2 = Intent(this,MainActivity::class.java)
                intent2.putExtra("RETURNED_DATA",binding.etvData.text.toString())
                setResult(200,intent2)
                finish()
            }
        }
    }
}