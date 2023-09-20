package com.example.androidlearning.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearning.databinding.SelectRcBinding
import com.example.androidlearning.model.Todo

class SelectAdaptor(private val changeAdaptor: () -> Unit) :
    RecyclerView.Adapter<SelectViewHolder>() {
    private val todoList = mutableListOf<Todo>()
    private val selectedTodoList = mutableSetOf<Todo>()
    var allCheck: Int = 0
    var initalPos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SelectViewHolder(SelectRcBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        if (allCheck == 1) {
            holder.setTodoCheck(true)
            selectedTodoList.add(todoList[position])
        }
        if (allCheck == -1) {
            holder.setTodoCheck(false)
            selectedTodoList.clear()
        }
        if (initalPos == position) {
            selectedTodoList.add(todoList[position])
            holder.setTodoCheck(true)
        }
        holder.setTodo(todoList[position])

//        if (holder.setOnClick()) {
//            selectedTodoList.add(todoList[position])
//        } else {
//            selectedTodoList.remove(todoList[position])
//            if (selectedTodoList.size == 0) {
//                changeAdaptor()
//            }
//        }
//        Log.i("Abhi", "Select Adaptor List: " + selectedTodoList.toString())

        holder.itemView.setOnClickListener {
            allCheck = 0
            holder.toggleSelection()
            if (holder.isSelected()) {
                selectedTodoList.add(todoList[position])
            } else {
                selectedTodoList.remove(todoList[position])
                if (selectedTodoList.size == 0) {
                    changeAdaptor()
                }
            }
            Log.i("Abhi", "Select Adaptor List: " + selectedTodoList.toString())
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTodoList(list: List<Todo>) {
        selectedTodoList.clear()
        todoList.clear()
        todoList.addAll(list)
    }

    fun selectedTodoList(): List<Todo> {
        return selectedTodoList.toList()
    }

    private fun setInitialMark(initalPos: Int) {
        notifyItemChanged(initalPos)
        selectedTodoList.add(todoList[initalPos])
    }

}

class SelectViewHolder(private val binding: SelectRcBinding) :
    RecyclerView.ViewHolder(binding.root) {


//    fun selectUnSelectItem(): Boolean {
//        binding.root.setOnClickListener {
//            binding.selectTodo.isChecked = !binding.selectTodo.isChecked
//            Log.i("Abhi", "select list item " + binding.selectTodo.isChecked)
//        }
//        return binding.selectTodo.isChecked
//    }

    fun setTodoCheck(checked: Boolean) {
        binding.selectTodo.isChecked = checked
    }

    fun setTodo(todo: Todo) {
        binding.todoTitleSelect.text = todo.title
        binding.todoDesSelect.text = todo.description
        binding.todoDueDateSelect.text = todo.dueDate
        if (todo.isCompleted) {
            binding.root.setBackgroundColor(Color.GREEN)
        } else {
            binding.root.setBackgroundColor(Color.WHITE)
        }
    }

    fun toggleSelection() {
        binding.selectTodo.isChecked = !binding.selectTodo.isChecked
    }

    fun isSelected(): Boolean {
        return binding.selectTodo.isChecked
    }

    fun setOnClick(): Boolean {
        binding.selectTodo.setOnClickListener {
            toggleSelection()
        }
        binding.root.setOnClickListener {
            toggleSelection()
        }
        return isSelected()
    }
}