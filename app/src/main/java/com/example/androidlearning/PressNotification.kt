package com.example.androidlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearning.databinding.ActivityPressNotificationBinding

class PressNotification : AppCompatActivity() {
    private lateinit var binding: ActivityPressNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPressNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}