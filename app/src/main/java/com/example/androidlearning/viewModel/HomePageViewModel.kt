package com.example.androidlearning.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidlearning.repository.TodoRepository
import com.example.androidlearning.model.Todo

class HomePageViewModel(private val repository: TodoRepository):ViewModel() {

    val todoList = repository.getAllTodos()

    fun getAllTodoByTitle(query: String): LiveData<List<Todo>>{
        return repository.getAllTodoByTitle(query)
    }

}