package com.example.androidlearning

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearning.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE)
        if (!sharedPreferences.getBoolean(UserSettings.LOGGED_IN.value,false)){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val userPreferredLanguage = sharedPreferences.getString(UserSettings.USER_ID.value,"0")

        binding.tvUserId.text = "User id: $userPreferredLanguage"

        binding.btnLogOut.setOnClickListener {
            clearLoginState()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clearLoginState() {
        sharedPreferences.edit().clear().apply()
    }
}