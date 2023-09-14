package com.example.androidlearning.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlearning.repository.TodoRepository
import com.example.androidlearning.model.Todo
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

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }
}
