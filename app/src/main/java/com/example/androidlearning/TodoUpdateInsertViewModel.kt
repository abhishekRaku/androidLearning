package com.example.androidlearning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoUpdateInsertViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todo = MutableLiveData<Todo>()
    val todo = _todo

    fun getTodoById(id: Long) = viewModelScope.launch {
        _todo.value =  repository.getTodoById(id)
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        repository.insertTodo(todo)
    }
}
