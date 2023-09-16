package com.example.androidlearning

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearning.databinding.TodoListBinding
import com.example.androidlearning.model.Todo
import com.example.androidlearning.ui.TodoUpateInsert

class TodoListAdaptor(private val goto: (Long) -> Unit): RecyclerView.Adapter<TodoViewHolder>() {

    private val todoList = mutableListOf<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoViewHolder(TodoListBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.setTodo(todoList[position])
        holder.goToo(goto, todoList[position])
//        holder.updateTodo(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTodoList(list: List<Todo>){
        Log.i("why","sdfsd")
        todoList.clear()
        todoList.addAll(list)
    }
}

class TodoViewHolder(private val binding: TodoListBinding): RecyclerView.ViewHolder(binding.root){

    fun setTodo(todo: Todo){
        binding.todoTitle.text = todo.title
        binding.todoDes.text = todo.description
        binding.todoDueDate.text = todo.dueDate
        if(todo.isCompleted){
            binding.root.setBackgroundColor(Color.GREEN)
        }
    }

    fun updateTodo(todo: Todo){
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, TodoUpateInsert::class.java)
            Log.i("Abhi", binding.root.context.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("TODO_ID", todo.id)
            binding.root.context.startActivity(intent)
        }
    }

    fun goToo(goto: (Long) -> Unit, todo: Todo){
        binding.root.setOnClickListener {
            goto(todo.id)
        }
    }
}