package com.example.androidlearning

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.androidlearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSend.setOnClickListener {
            if(!binding.etvName.text.isNullOrEmpty()){
                val intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("GIVEN_DATA", binding.etvName.text.toString())
                startActivity.launch(intent)
            }
        }
    }

    val startActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == 200 && result.data != null) {
                binding.tvReturnResult.text =
                    "Returned Result is ${result.data!!.getStringExtra("RETURNED_DATA")}"
            }
        }
}
