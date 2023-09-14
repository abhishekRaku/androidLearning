package com.example.androidlearning.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidlearning.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * from todo_table")
    fun getAllTodos(): LiveData<List<Todo>>
    // in LiveData or flow suspend keyword is not necessary room will execute it automatically in background

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * from todo_table WHERE id = :id")
    suspend fun getTodoById(id: Long): Todo?

    @Query("SELECT * FROM todo_table WHERE title LIKE :query")
    fun getAllTodoByTitle(query: String): LiveData<List<Todo>>
}