package com.example.androidlearning

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidlearning.databinding.ActivityMainBinding
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("my_data", MODE_PRIVATE)

        binding.btnSign.setOnClickListener {
            if(binding.etvId.text.toString().isEmpty() || binding.etvPass.text.toString().isEmpty()){
                Toast.makeText(this,"Field cannot be empty",Toast.LENGTH_SHORT).show()
            }else{
                saveUserInstance(binding.etvId.text.toString())
                val intent = Intent(this,HomePage::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveUserInstance(value: String) {
        sharedPreferences.edit().apply {
            putBoolean(UserSettings.LOGGED_IN.value,true)
            putString(UserSettings.USER_ID.value,value)
            commit()
        }
    }

}
