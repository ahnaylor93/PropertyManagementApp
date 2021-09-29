package com.example.propertymanagementapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.sql.Todo
import com.example.propertymanagementapp.sql.dao.TodoDao

class TodoViewModel(val todoDao: TodoDao):ViewModel() {
    val todos: LiveData<List<Todo>> = todoDao.getTodo()
    val insertId: MutableLiveData<Long> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun addTodo(todo:Todo){
        val id=todoDao.addTodo(todo)

        if(id>0){
            insertId.postValue(id)
            error.postValue("")
        }
        else{
            error.postValue("Failed to insert. please retry")
        }
    }
}