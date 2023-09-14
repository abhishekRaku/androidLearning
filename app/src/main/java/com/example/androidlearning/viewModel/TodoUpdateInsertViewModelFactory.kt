package com.example.androidlearning.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidlearning.repository.TodoRepository

class TodoUpdateInsertViewModelFactory(private val repository: TodoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoUpdateInsertViewModel::class.java)) {
            return TodoUpdateInsertViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}