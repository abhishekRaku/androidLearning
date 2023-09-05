package com.example.androidlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidlearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ChatFragment())
    }

    override fun onResume() {
        super.onResume()
        binding.myBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.chatList -> replaceFragment(ChatFragment())
                R.id.myCalls -> replaceFragment(CallFragment())
                R.id.myHome -> replaceFragment(HomeFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.myFrameLayout.id,fragment)
        fragmentTransaction.commit()
    }
}