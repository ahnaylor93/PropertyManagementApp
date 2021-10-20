package com.example.propertymanagementapp.fragment.tenants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.activity.TenantsScreenActivity
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.databinding.FragmentSelectPropertyBinding
import com.example.propertymanagementapp.databinding.FragmentViewPropertiesBinding
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModel
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModelFactory

class SelectPropertyFragment:DialogFragment() {
    //lateinit var binding:FragmentSelectPropertyBinding
    private lateinit var viewModel: GetPropertiesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = GetPropertiesViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(GetPropertiesViewModel::class.java)
        val userId=this.activity?.getSharedPreferences("app_settings",
            AppCompatActivity.MODE_PRIVATE)?.getString("userId",null)
        try {
            //binding = FragmentViewPropertiesBinding.inflate(layoutInflater, container, false)
            val binding = DataBindingUtil.inflate<FragmentSelectPropertyBinding>(
                inflater,
                R.layout.fragment_select_property,
                container,
                false
            )
            setHasOptionsMenu(true)
            binding.rvProperty.layoutManager = LinearLayoutManager(this.context)
            binding.selectfragment=this
            binding.viewModel = viewModel
            if (userId != null) {
                viewModel.loadPropertiesByUser(userId)
            }
            return binding.root
        } catch (e: Exception) {
            Log.d("FRAGMENT ERROR", e.message ?: "UNKNOWN")
            throw e
        }
    }

}
