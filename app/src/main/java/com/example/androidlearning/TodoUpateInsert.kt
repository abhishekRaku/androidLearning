package com.example.androidlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidlearning.databinding.TodoUpdateInsertBinding

class TodoUpateInsert : AppCompatActivity() {
    private lateinit var binding: TodoUpdateInsertBinding
    private lateinit var viewModel: TodoUpdateInsertViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoUpdateInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TodoRepository(MyDatabase.getInstance(applicationContext).todoDao())
        viewModel = TodoUpdateInsertViewModelFactory(repository).create(TodoUpdateInsertViewModel::class.java)

        var todoId = intent.getLongExtra("TODO_ID",-999)
        Log.i("Abhi", "Update1 " + todoId.toString())
        if(todoId!= (-999).toLong()){
            Log.i("Abhi", "Update " + todoId.toString())
            setTodo(todoId)
        } else{
            todoId = 0
        }
        binding.saveUpdateBtn.setOnClickListener {
           insertTodo(todoId)
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }

    private fun insertTodo(id:Long) {
        viewModel.insertTodo(
            Todo(
            id,
            binding.etvTodoTitle.text.toString(),
            binding.etvTodoDes.text.toString(),
            false
        )
        )
    }

    private fun setTodo(id: Long) {
        viewModel.getTodoById(id)
        viewModel.todo.observe(this) {
            Log.i("Abhi", it.toString())
            if (it == null) {
                Toast.makeText(this, "Some Error Occured", Toast.LENGTH_SHORT).show()
            } else {
                binding.etvTodoTitle.setText(it.title)
                binding.etvTodoDes.setText(it.description)
                binding.saveUpdateBtn.setText("Update")
                // date and checked later
            }
        }

    }
}