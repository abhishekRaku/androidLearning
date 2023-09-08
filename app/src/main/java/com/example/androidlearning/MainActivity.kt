package com.example.androidlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidlearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaController: MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaController = MediaController(this)
    }

    override fun onResume() {
        super.onResume()

        binding.btnPickImage.setOnClickListener {
            setImage.launch("image/*")
        }

        binding.btnPickVideo.setOnClickListener {
            setVideo.launch("video/*")
        }
    }

    private val setImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.imageView.setImageURI(uri)
    }

    private val setVideo = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if(uri!=null) {
            binding.videoView.setVideoURI(uri)
            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.requestFocus()
        }
    }
}