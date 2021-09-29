package com.example.propertymanagementapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.holder.TodoViewHolder
import com.example.propertymanagementapp.sql.Todo

class TodoAdapter (val todos:ArrayList<Todo>):RecyclerView.Adapter<TodoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding=layoutInflater.inflate(R.layout.view_holder_todo,parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int {
        return todos.size
    }
    fun setNewData(newList: List<Todo>) {
        todos.clear()
        todos.addAll(newList)
        notifyDataSetChanged()
    }
}