package com.example.androidlearning.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidlearning.R
import com.example.androidlearning.databinding.TodoUpdateInsertBinding
import com.example.androidlearning.db.MyDatabase
import com.example.androidlearning.model.Todo
import com.example.androidlearning.repository.TodoRepository
import com.example.androidlearning.viewModel.TodoUpdateInsertViewModel
import com.example.androidlearning.viewModel.TodoUpdateInsertViewModelFactory
import java.util.*


class TodoUpateInsert : AppCompatActivity() {
    private lateinit var binding: TodoUpdateInsertBinding
    private lateinit var viewModel: TodoUpdateInsertViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoUpdateInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TodoRepository(MyDatabase.getInstance(applicationContext).todoDao())
        val factory = TodoUpdateInsertViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TodoUpdateInsertViewModel::class.java)

        var todoId = intent.getLongExtra("TODO_ID", -999)
        Log.i("Abhi", "Update1 " + todoId.toString())
        if (todoId != (-999).toLong()) {
            Log.i("Abhi", "Update " + todoId.toString())
            viewModel.getTodoById(todoId)
            setTodo()
        } else {
            todoId = 0
        }

        binding.saveUpdateBtn.setOnClickListener {
            if (binding.etvTodoTitle.text.toString().isNotEmpty()) {
                insertTodo(todoId)
                goToHomePage()
            } else Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
        }

        binding.btnCancelDelete.setOnClickListener {
            Log.i("Abhi", "hello" + binding.btnCancelDelete.text.toString())
        }

        binding.btnCancelDelete.setOnClickListener {
            cancelDeleteTodo()
        }

    }

    private fun goToHomePage() {
        Log.i("Abhi", "home")
        val intent = Intent(this, HomePage::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun cancelDeleteTodo() {
        val cancel = resources.getString(R.string.CancelTodo)
        if (binding.btnCancelDelete.text.toString().equals(cancel)) {
            goToHomePage()
        } else {
            viewModel.todo.observe(this) {
                viewModel.deleteTodo(it)
            }
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            goToHomePage()
        }
    }

    private fun insertTodo(id: Long) {
        viewModel.insertTodo(
            Todo(
                id,
                binding.etvTodoTitle.text.toString(),
                binding.etvTodoDes.text.toString(),
                binding.etvDate.text.toString(),
                binding.checkTodo.isChecked
            )
        )
    }

    private fun setTodo() {
        viewModel.todo.observe(this) {
            Log.i("Abhi", it.toString())
            if (it == null) {
                Toast.makeText(this, "Some Error Occured", Toast.LENGTH_SHORT).show()
            } else {
                binding.etvTodoTitle.setText(it.title)
                binding.etvTodoDes.setText(it.description)
                binding.etvDate.setText(it.dueDate)
                binding.checkTodo.isChecked = it.isCompleted
                binding.saveUpdateBtn.setText(R.string.UpdateTodo)
                binding.btnCancelDelete.setText(R.string.DeleteTodo)
                // date and checked later
            }
        }

    }

    fun datePicker(view: View) {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { datePicker: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                // Handle the selected date here
                val selectedDate =
                    selectedDay.toString() + "/" + (selectedMonth + 1) + "/" + selectedYear.toString()
                binding.etvDate.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}