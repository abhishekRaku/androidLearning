package com.example.androidlearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TodoUpdateInsertViewModelFactory(private val repository: TodoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoUpdateInsertViewModel::class.java)) {
            return TodoUpdateInsertViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}