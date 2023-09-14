package com.example.androidlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearning.databinding.HomePageBinding

class HomePage : AppCompatActivity() {
    private lateinit var binding: HomePageBinding
    private lateinit var todoListAdapter: TodoListAdaptor
    private lateinit var viewModel: HomePageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TodoRepository(MyDatabase.getInstance(applicationContext).todoDao())
        viewModel = HomePageViewModelFactory(repository).create(HomePageViewModel::class.java)
        // have to read about factory

        // initializing todo list
        intializeTodoList()


        binding.fabTodo.setOnClickListener {
            val intent = Intent(this, TodoUpateInsert::class.java)
            startActivity(intent)
        }
    }

    fun intializeTodoList() {
        binding.myRcTodoList.layoutManager = LinearLayoutManager(this)
        todoListAdapter = TodoListAdaptor()
        binding.myRcTodoList.adapter = todoListAdapter
        displayTodoList()
    }

    private fun displayTodoList() {
        viewModel.todoList.observe(this) {
            Log.i("Abhi", it.toString())
            Log.i("Abhi", "adaptor " + binding.root.context.toString())
            todoListAdapter.setTodoList(it)
            todoListAdapter.notifyDataSetChanged()
        }
    }
}

