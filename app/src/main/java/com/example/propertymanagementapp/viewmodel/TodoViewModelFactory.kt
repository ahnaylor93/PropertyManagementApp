package com.example.propertymanagementapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapp.sql.dao.TodoDao

class TodoViewModelFactory (val todoDao: TodoDao): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return TodoViewModel(todoDao) as T
    }
}