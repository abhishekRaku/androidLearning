package com.example.androidlearning.repository

import com.example.androidlearning.dao.TodoDao
import com.example.androidlearning.model.Todo


class TodoRepository(private val todoDao: TodoDao) {

    fun getAllTodos() = todoDao.getAllTodos()

    suspend fun insertTodo(todo: Todo) = todoDao.insertTodo(todo)

    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)

    suspend fun getTodoById(id: Long) = todoDao.getTodoById(id)

    fun getAllTodoByTitle(query: String) = todoDao.getAllTodoByTitle(query)

    companion object {
        @Volatile
        private var instance: TodoRepository? = null

        fun getInstance(todoDao: TodoDao): TodoRepository {
            synchronized(this) {
                if (instance == null) {
                    instance = TodoRepository(todoDao)
                }
                return instance!!
            }
        }
    }
}