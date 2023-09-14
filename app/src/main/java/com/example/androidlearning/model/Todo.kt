package com.example.androidlearning.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "todo_table")
data class Todo(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "due_date")
    val dueDate: String,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean,
//    @ColumnInfo(name = "temp")
//    val temp: Boolean
)
