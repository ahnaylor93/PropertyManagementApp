package com.example.propertymanagementapp.fragment.toDoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapp.databinding.FragmentAddTodoBinding
import com.example.propertymanagementapp.sql.AppDatabase
import com.example.propertymanagementapp.sql.Todo
import com.example.propertymanagementapp.viewmodel.TodoViewModel
import com.example.propertymanagementapp.viewmodel.TodoViewModelFactory
import android.R
import android.graphics.Color


class AddToDoFragment :Fragment(){
    lateinit var binding: FragmentAddTodoBinding
    lateinit var viewModel: TodoViewModel
    var dateStr:String ="9/ 30/ 2021"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val tenantDao = AppDatabase.getInstance(requireActivity()).todoDao
        val factory = TodoViewModelFactory(tenantDao)
        viewModel = ViewModelProvider(requireActivity(), factory).get(TodoViewModel::class.java)
        binding.cvDueDay.setOnDateChangeListener(){
                view, year, month, dayOfMonth ->

            dateStr =  "${(month + 1)}/ $dayOfMonth/ $year"

        }
        binding.btnAdd.setOnClickListener(){
            val title = binding.etTitle.text.toString()
            val desc = binding.etDesc.text.toString()
            val due = dateStr
            val todo = Todo(0,title,desc,due)

            viewModel.addTodo(todo)
        }

    }
}