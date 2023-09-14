package com.example.androidlearning.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidlearning.repository.TodoRepository
import com.example.androidlearning.model.Todo

class HomePageViewModel(private val repository: TodoRepository):ViewModel() {

    private var _todoList = repository.getAllTodos()
    val todoList: LiveData<List<Todo>> = _todoList

    fun updateTodoList(){
        _todoList = repository.getAllTodos()
    }

}