package com.example.androidlearning.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidlearning.repository.TodoRepository

class HomePageViewModelFactory(private val repository: TodoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomePageViewModel::class.java)) { //If the modelClass is not compatible with HomePageViewModel, it throws an IllegalArgumentException with the message "Unknown ViewModel class." This is a safety check to ensure that you only create instances of the expected ViewModel class.
            return HomePageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
