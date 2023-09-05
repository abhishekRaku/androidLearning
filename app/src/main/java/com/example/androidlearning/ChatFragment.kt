package com.example.androidlearning

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidlearning.databinding.ChatFragmentBinding

class ChatFragment: Fragment(R.layout.chat_fragment) {

    private lateinit var binding: ChatFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.clickMeBtnChat.setOnClickListener {
            Toast.makeText(context,"This is Chat Fragment", Toast.LENGTH_SHORT).show()
        }
    }
}