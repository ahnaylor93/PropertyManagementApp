package com.example.propertymanagementapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.adapter.TodoAdapter
import com.example.propertymanagementapp.databinding.ActivityMainBinding
import com.example.propertymanagementapp.databinding.ActivityToDoListScreenBinding
import com.example.propertymanagementapp.fragment.toDoList.AddToDoFragment
import com.example.propertymanagementapp.sql.AppDatabase
import com.example.propertymanagementapp.viewmodel.TodoViewModel
import com.example.propertymanagementapp.viewmodel.TodoViewModelFactory

class ToDoListScreenActivity : AppCompatActivity() {
    lateinit var binding:ActivityToDoListScreenBinding
    lateinit var viewModel:TodoViewModel
    lateinit var adapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        adapter = TodoAdapter(ArrayList())
        binding.rvTodo.adapter =adapter

        setUpViewModel()
        setUpEvents()
        setUpObservers()
    }
    fun setUpObservers(){
        viewModel.error.observe(this){
            if (it.isNotEmpty()){
                Toast.makeText(baseContext,it, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.insertId.observe(this){
            Toast.makeText(baseContext,"added successfully", Toast.LENGTH_LONG).show()
        }
        viewModel.todos.observe(this){
            adapter.setNewData(it)
        }
    }
    fun setUpViewModel(){
        val tenantDao = AppDatabase.getInstance(this).todoDao
        val factory = TodoViewModelFactory(tenantDao)
        viewModel = ViewModelProvider(this, factory).get(TodoViewModel::class.java)
    }
    fun setUpEvents(){
        binding.btnAddTodo.setOnClickListener(){
            loadAddToDoFragment()
        }
    }
    fun loadAddToDoFragment(){
        val addToDoFragment = AddToDoFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.todo_container, addToDoFragment)
            .addToBackStack("AddToDoFragment")
            .commit()
    }
}