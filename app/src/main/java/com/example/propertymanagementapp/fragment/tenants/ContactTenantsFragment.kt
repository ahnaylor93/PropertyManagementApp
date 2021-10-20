package com.example.propertymanagementapp.fragment.tenants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.databinding.FragmentContactTetantsBinding
import com.example.propertymanagementapp.databinding.FragmentViewPropertiesBinding
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModel
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModelFactory
import com.example.propertymanagementapp.viewmodel.GetTenantsViewModel
import com.example.propertymanagementapp.viewmodel.GetTenantsViewModelFactory

class ContactTenantsFragment:Fragment() {
    lateinit var viewModel:GetTenantsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = GetTenantsViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this,factory).get(GetTenantsViewModel::class.java)
        val userId=this.activity?.getSharedPreferences("app_settings",
            AppCompatActivity.MODE_PRIVATE)?.getString("userId",null)
        try {
            //binding = FragmentViewPropertiesBinding.inflate(layoutInflater, container, false)
            val binding = DataBindingUtil.inflate<FragmentContactTetantsBinding>(
                inflater,
                R.layout.fragment_contact_tetants,
                container,
                false
            )
            setHasOptionsMenu(true)
            binding.rvTenants.layoutManager = LinearLayoutManager(this.context)
            binding.viewModel = viewModel
            /*if (userId != null) {
                viewModel.getTenantsByLandlord(userId)
            }*/

            viewModel.getTenant()
            return binding.root
        } catch (e: Exception) {
            Log.d("FRAGMENT ERROR", e.message ?: "UNKNOWN")
            throw e
        }
    }
}