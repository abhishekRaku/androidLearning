package com.example.androidlearning

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidlearning.databinding.ChatFragmentBinding

class ChatFragment: Fragment(R.layout.chat_fragment) {

    private lateinit var binding: ChatFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentBinding.bind(view)
        Log.i("Abhi","chat onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        binding.clickMeBtnChat.setOnClickListener {
            Toast.makeText(context,"This is Chat Fragment", Toast.LENGTH_SHORT).show()
        }
        Log.i("Abhi","chat onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Abhi","chat onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Abhi","chat onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Abhi","chat onStop")
    }
}