package com.example.propertymanagementapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.api.response.Tenant
import com.example.propertymanagementapp.databinding.HolderTenantBinding
import com.example.propertymanagementapp.holder.PropertyHolder
import com.example.propertymanagementapp.holder.TenantHolder

class TenantAdapter(val tenants:List<Tenant>):RecyclerView.Adapter<TenantHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v= DataBindingUtil.inflate<HolderTenantBinding>(inflater,
            R.layout.holder_tenant,parent,false)
        return TenantHolder(v)
    }

    override fun onBindViewHolder(holder: TenantHolder, position: Int) {
        holder.bind(tenants[position])
    }

    override fun getItemCount(): Int {
        return tenants.size
    }

}