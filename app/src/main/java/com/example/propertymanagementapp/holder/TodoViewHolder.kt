package com.example.propertymanagementapp.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.databinding.ViewHolderTodoBinding
import com.example.propertymanagementapp.sql.Todo

class TodoViewHolder(val v: View):RecyclerView.ViewHolder(v) {
    val title: TextView
    val desc: TextView
    val dueDay: TextView
    lateinit var binding:ViewHolderTodoBinding

    init {
        title = v.findViewById(R.id.tv_title)
        desc = v.findViewById(R.id.tv_description)
        dueDay = v.findViewById(R.id.tv_due)
        binding=ViewHolderTodoBinding.bind(v)

    }

    fun bind(todo: Todo){
        title.text="${todo.title}"
        desc.text="${todo.description}"
        dueDay.text="${todo.dueDay}"
    }
}