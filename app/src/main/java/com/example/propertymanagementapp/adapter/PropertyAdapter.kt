package com.example.propertymanagementapp.adapter

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.databinding.HolderPropertyBinding
import com.example.propertymanagementapp.holder.PropertyHolder

class PropertyAdapter(val properties: List<Property>): RecyclerView.Adapter<PropertyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HolderPropertyBinding = DataBindingUtil.inflate(layoutInflater, R.layout.holder_property, parent, false)
        return PropertyHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyHolder, position: Int) {
        holder.bind(properties[position])
    }

    override fun getItemCount() = properties.size
}