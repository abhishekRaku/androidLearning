package com.example.androidlearning.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlearning.model.Todo
import com.example.androidlearning.repository.TodoRepository
import kotlinx.coroutines.launch

class HomePageViewModel(private val repository: TodoRepository):ViewModel() {

    val todoList = repository.getAllTodos()

    fun getAllTodoByTitle(query: String): LiveData<List<Todo>>{
        return repository.getAllTodoByTitle(query)
    }

    fun deleteAllTodosById(ids: List<Todo>) = viewModelScope.launch {
        repository.deleteAllTodosById(ids)
    }

}