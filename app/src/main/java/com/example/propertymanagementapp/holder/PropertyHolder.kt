package com.example.propertymanagementapp.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.databinding.HolderPropertyBinding

class PropertyHolder(val binding: HolderPropertyBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(property: Property){
        binding.property = property
    }
}