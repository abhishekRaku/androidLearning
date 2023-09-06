package com.example.androidlearning

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidlearning.databinding.CallFragmentBinding

class CallFragment: Fragment(R.layout.call_fragment) {

    private lateinit var binding: CallFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CallFragmentBinding.bind(view)
        Log.i("Abhi","call onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        binding.clickMeBtnCalls.setOnClickListener {
            Toast.makeText(context,"This is Call Fragment", Toast.LENGTH_SHORT).show()
        }
        Log.i("Abhi","call onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Abhi","call onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Abhi","call onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Abhi","call onStop")
    }

}