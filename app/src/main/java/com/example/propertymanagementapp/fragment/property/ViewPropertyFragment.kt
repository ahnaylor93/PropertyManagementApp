package com.example.propertymanagementapp.fragment.property

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.databinding.FragmentAddPropertyBinding
import com.example.propertymanagementapp.databinding.FragmentViewPropertiesBinding
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModel
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModelFactory

class ViewPropertyFragment : Fragment() {
    private lateinit var viewModel: GetPropertiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = GetPropertiesViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(GetPropertiesViewModel::class.java)

        try {
            //binding = FragmentViewPropertiesBinding.inflate(layoutInflater, container, false)
            val binding = DataBindingUtil.inflate<FragmentViewPropertiesBinding>(
                inflater,
                R.layout.fragment_view_properties,
                container,
                false
            )
            setHasOptionsMenu(true)
            binding.rvProperties.layoutManager = LinearLayoutManager(this.context)
            return binding.root
        } catch (e: Exception) {
            Log.d("FRAGMENT ERROR", e.message ?: "UNKNOWN")
            throw e
        }
    }
}