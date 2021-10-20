package com.example.propertymanagementapp.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.api.response.Tenant
import com.example.propertymanagementapp.databinding.HolderTenantBinding

class TenantHolder(val binding:HolderTenantBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(tenant: Tenant){
        binding.tenant = tenant
    }
}