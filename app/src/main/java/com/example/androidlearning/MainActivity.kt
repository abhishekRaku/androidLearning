package com.example.androidlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notifications: MyNotifications
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notifications = MyNotifications(this)

        binding.btnHeadsUpNotification.setOnClickListener {
            notifications.headsUpNotification()
        }

        binding.btnExpandable.setOnClickListener {
            notifications.expandableNotification()
        }

        binding.btnActionNoti.setOnClickListener {
            notifications.actionNotification()
        }

        binding.btnProgress.setOnClickListener {
            notifications.progressNotification()
        }

        binding.btnReplyNoti.setOnClickListener {
            notifications.replyActionNotification()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        binding.btnSimple.setOnClickListener {
//            notifications.showNotification()
//        }
//    }

}
