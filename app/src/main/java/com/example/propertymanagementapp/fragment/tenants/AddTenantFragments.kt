package com.example.propertymanagementapp.fragment.tenants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.activity.TenantsScreenActivity
import com.example.propertymanagementapp.databinding.FragmentAddPropertyBinding
import com.example.propertymanagementapp.databinding.FragmentAddTenantBinding
import com.example.propertymanagementapp.viewmodel.AddPropertyViewModel
import com.example.propertymanagementapp.viewmodel.AddTenantViewModel

class AddTenantFragments:Fragment() {
    lateinit var binding:FragmentAddTenantBinding
    lateinit var viewModel: AddTenantViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val propertyId = TenantsScreenActivity.selectedProperty?._id

        try {
            binding = DataBindingUtil.inflate<FragmentAddTenantBinding>(
                inflater,
                R.layout.fragment_add_tenant,
                container,
                false
            )
            setHasOptionsMenu(true)

        } catch (e: Exception) {
            Log.d("FRAGMENT ERROR", e.message ?: "UNKNOWN")
            throw e
        }
        viewModel = AddTenantViewModel(propertyId,binding)
        binding.viewModel = viewModel
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {

        viewModel.addTenantResponse.observe(viewLifecycleOwner) {

            if(it.error==false) {
                Toast.makeText(activity, "add tenant successful", Toast.LENGTH_LONG).show()
                binding.etEmail.text.clear()
                binding.etName.text.clear()
            }
            else{
                it?.message?.let {
                        msg ->
                    Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
    }
}