package com.example.androidlearning.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidlearning.model.Todo
import com.example.androidlearning.repository.TodoRepository
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: TodoRepository): ViewModel() {


    val todoList: LiveData<List<Todo>> = repository.getAllTodos()

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        repository.insertTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }
}

class SharedViewModelFactory private constructor(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        // Create a singleton instance of the factory
        @Volatile
        private var instance: SharedViewModelFactory? = null

        fun getInstance(repository: TodoRepository): SharedViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: SharedViewModelFactory(repository).also { instance = it }
            }
        }
    }
}