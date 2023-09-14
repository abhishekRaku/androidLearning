package com.example.androidlearning

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
//    @ColumnInfo(name = "due_date")
//    val dueDate: Date,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean
)
