package com.example.propertymanagementapp.sql

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.FileDescriptor
@Entity(tableName = "todo_table")
class Todo (

    @ColumnInfo(name = "todo_id")
    @PrimaryKey(autoGenerate = true)
    var todoId: Long,
    @ColumnInfo(name="title")
    val title:String,
    @ColumnInfo(name="description")
    val description:String,
    @ColumnInfo(name="due_day")
    val dueDay:String
        )