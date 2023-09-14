package com.example.androidlearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HomePageViewModel(private val repository: TodoRepository):ViewModel() {

    private var _todoList = repository.getAllTodos()
    val todoList: LiveData<List<Todo>> = _todoList

    fun updateTodoList(){
        _todoList = repository.getAllTodos()
    }

}