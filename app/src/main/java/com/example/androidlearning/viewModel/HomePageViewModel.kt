package com.example.androidlearning.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidlearning.model.Todo
import com.example.androidlearning.repository.TodoRepository

class HomePageViewModel(private val repository: TodoRepository):ViewModel() {

    val todoList = repository.getAllTodos()


//    fun getAllTodo(): LiveData<List<Todo>>{
//        return repository.getAllTodos()
//    }

    fun getAllTodoByTitle(query: String): LiveData<List<Todo>>{
        return repository.getAllTodoByTitle(query)
    }

}