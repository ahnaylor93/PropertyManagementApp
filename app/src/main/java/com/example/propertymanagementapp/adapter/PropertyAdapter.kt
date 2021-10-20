package com.example.propertymanagementapp.adapter

import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.activity.TenantsScreenActivity
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.databinding.HolderPropertyBinding
import com.example.propertymanagementapp.holder.PropertyHolder

class PropertyAdapter(val properties: List<Property>): RecyclerView.Adapter<PropertyHolder>() {
    var propertyClickListener: ((Property)-> Unit)?=null
    fun setOnPropertySelectListener(listener:(Property) ->Unit){
        propertyClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyHolder {
        /*val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HolderPropertyBinding = DataBindingUtil.inflate(layoutInflater, R.layout.holder_property, parent, false)
        return PropertyHolder(binding)*/
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<HolderPropertyBinding>(inflater,R.layout.holder_property,parent,false)
        return PropertyHolder(v)
    }

    override fun onBindViewHolder(holder: PropertyHolder, position: Int) {
        holder.bind(properties[position])
        /*if(this::propertyClickListener.isInitialized) {
            holder.itemView.setOnClickListener {
                propertyClickListener(properties[position], position)
            }
        }*/
        holder.itemView.setOnClickListener {
            propertyClickListener?.let { it1 -> it1(properties[position]) }
            Log.d("click","${properties[position].propertyStatus}")

        }
    }

    override fun getItemCount() = properties.size
}