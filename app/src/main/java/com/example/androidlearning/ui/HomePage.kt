package com.example.androidlearning.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        val repository = TodoRepository.getInstance(MyDatabase.getInstance(applicationContext).todoDao)

        val factory = HomePageViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomePageViewModel::class.java)
//        viewModel = HomePageViewModelFactory(repository).create(HomePageViewModel::class.java)
        // have to read about factory

        // initializing todo list

        binding.fabTodo.setOnClickListener {
            val intent = Intent(this, TodoUpateInsert::class.java)
            startActivity(intent)
        }
        Log.i("why","whyyyy")

        intializeTodoList()
        displayTodoList()
//        searchTodo()
    }

//    override fun onResume() {
//        super.onResume()
//        displayTodoList()
//    }

    fun intializeTodoList() {
        binding.myRcTodoList.layoutManager = LinearLayoutManager(this)
        todoListAdapter = TodoListAdaptor()
        binding.myRcTodoList.adapter = todoListAdapter
    }

    private fun displayTodoList() {
        viewModel.todoList.observe(this, Observer {
            todoListAdapter.setTodoList(it)
            todoListAdapter.notifyDataSetChanged()
        })
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

            @SuppressLint("SuspiciousIndentation")
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

