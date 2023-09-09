package com.example.androidlearning

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidlearning.databinding.HomeLayoutBinding

class HomeFragment: Fragment(R.layout.home_layout) {

    private lateinit var binding: HomeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeLayoutBinding.bind(view)
    }

    override fun onResume() {
        super.onResume()
        binding.clickMeBtnHome.setOnClickListener {
            Toast.makeText(context,"This is Home Fragment", Toast.LENGTH_SHORT).show()
        }
    }
}