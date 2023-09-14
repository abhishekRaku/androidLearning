package com.example.androidlearning


class TodoRepository(private val todoDao: TodoDao) {

    fun getAllTodos() = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) = todoDao.insertTodo(todo)

    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)

    suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo)

    suspend fun getTodoById(id: Long) = todoDao.getTodoById(id)

}