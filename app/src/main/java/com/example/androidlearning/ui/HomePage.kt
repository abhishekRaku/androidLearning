package com.example.androidlearning.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    private lateinit var selectAdaptor: SelectAdaptor
    private lateinit var viewModel: HomePageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TodoRepository(MyDatabase.getInstance(applicationContext).todoDao())
        val factory = HomePageViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomePageViewModel::class.java)

        // initializing todo list
        intializeTodoList()
        searchTodo()

        binding.fabTodo.setOnClickListener {
            val intent = Intent(this, TodoUpateInsert::class.java)
            startActivity(intent)
        }

        binding.btnDeleteAllTodo.setOnClickListener {
            Log.i("Temp", selectAdaptor.selectedTodoList().map { it.id }.toString())
            viewModel.deleteAllTodosById(selectAdaptor.selectedTodoList())
            intializeTodoList()
        }

        binding.selectAllTodo.setOnClickListener {
            if (selectAdaptor.allCheck == 0) {
                selectAdaptor.allCheck = 1
            } else {
                intializeTodoList()
            }
            selectAdaptor.notifyDataSetChanged()
        }
    }

    fun intializeTodoList() {
        binding.myRcTodoList.layoutManager = LinearLayoutManager(this)
//        binding.myRcTodoList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        todoListAdapter = TodoListAdaptor { pos: Int ->
            setSelectAdaptor(pos)
        }
        binding.myRcTodoList.adapter = todoListAdapter

        // showing search bar
        binding.todoSearch.visibility = View.VISIBLE
        binding.SelectAllLayout.visibility = View.INVISIBLE

        displayTodoList()
    }

    private fun displayTodoList() {
        viewModel.todoList.observe(this) {
            Log.i("Abhi", it.toString())
            todoListAdapter.setTodoList(it)
            todoListAdapter.notifyDataSetChanged()
        }
    }

    private fun searchTodo() {
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

            fun updateList(query: String) {
                viewModel.getAllTodoByTitle(query).observe(this@HomePage) {
                    todoListAdapter.setTodoList(it)
                    todoListAdapter.notifyDataSetChanged()
                }
            }

        })
    }

    private fun setSelectAdaptor(initialPos: Int) {
        Log.i("Abhi", "Select Adaptor invoke")
        binding.myRcTodoList.layoutManager = LinearLayoutManager(this)
//        binding.myRcTodoList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        selectAdaptor = SelectAdaptor() {
            intializeTodoList()
        }
        selectAdaptor.initalPos = initialPos
        binding.myRcTodoList.adapter = selectAdaptor
        // hiding search bar and showing other bar
        binding.todoSearch.visibility = View.INVISIBLE
        binding.SelectAllLayout.visibility = View.VISIBLE
        displaySelectList()
    }

    private fun displaySelectList() {
        viewModel.todoList.observe(this) {
            Log.i("Abhi", it.toString())
            selectAdaptor.setTodoList(it)
            selectAdaptor.notifyDataSetChanged()
        }
    }

}

