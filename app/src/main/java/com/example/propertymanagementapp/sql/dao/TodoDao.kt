package com.example.propertymanagementapp.sql.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.propertymanagementapp.sql.Todo

@Dao
interface TodoDao {


    @Insert
    fun addTodo(todo: Todo):Long

    @Query("SELECT * FROM todo_table")
    fun getTodo():LiveData<List<Todo>>
}