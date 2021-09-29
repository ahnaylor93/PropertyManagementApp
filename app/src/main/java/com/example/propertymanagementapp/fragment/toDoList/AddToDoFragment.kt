package com.example.propertymanagementapp.fragment.toDoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.propertymanagementapp.databinding.FragmentAddTodoBinding

class AddToDoFragment :Fragment(){
    lateinit var binding: FragmentAddTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}