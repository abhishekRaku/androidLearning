package com.example.androidlearning.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearning.TodoListAdaptor
import com.example.androidlearning.databinding.HomePageBinding
import com.example.androidlearning.db.MyDatabase
import com.example.androidlearning.repository.TodoRepository
import com.example.androidlearning.viewModel.HomePageViewModel
import com.example.androidlearning.viewModel.HomePageViewModelFactory

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

       searchTodo()
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
            todoListAdapter.setTodoList(it)
            todoListAdapter.notifyDataSetChanged()
        }
    }

    private fun searchTodo(){
        binding.todoSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.todoSearch.clearFocus()
                val searchQuery = "%$query%"
                updateList(searchQuery)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchQuery = "%$newText%"
                    updateList(searchQuery)
                    return false
            }

            fun updateList(query: String){
                viewModel.getAllTodoByTitle(query).observe(this@HomePage) {
                    todoListAdapter.setTodoList(it)
                    todoListAdapter.notifyDataSetChanged()
                }
            }

        })
    }

}

