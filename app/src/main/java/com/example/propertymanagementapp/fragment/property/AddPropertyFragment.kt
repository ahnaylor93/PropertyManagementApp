package com.example.propertymanagementapp.fragment.property

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.databinding.FragmentAddPropertyBinding
import com.example.propertymanagementapp.databinding.FragmentViewPropertiesBinding
import com.example.propertymanagementapp.viewmodel.AddPropertyViewModel
import com.example.propertymanagementapp.viewmodel.GetPropertiesViewModel
import com.example.propertymanagementapp.viewmodel.RegisterViewModel
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory

class AddPropertyFragment : Fragment() {
    lateinit var binding: FragmentAddPropertyBinding
    lateinit var viewModel: AddPropertyViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userId=this.activity?.getSharedPreferences("app_settings",
            AppCompatActivity.MODE_PRIVATE)?.getString("userId",null)
        val userType = this.activity?.getSharedPreferences("app_settings",
            AppCompatActivity.MODE_PRIVATE)?.getString("type",null)


        try {
            //binding = FragmentAddPropertyBinding.inflate(layoutInflater, container, false)
            binding = DataBindingUtil.inflate<FragmentAddPropertyBinding>(
                inflater,
                R.layout.fragment_add_property,
                container,
                false
            )
            setHasOptionsMenu(true)

        } catch (e: Exception) {
            Log.d("FRAGMENT ERROR", e.message ?: "UNKNOWN")
            throw e
        }
        viewModel = AddPropertyViewModel(userId,userType,binding)
        //viewModel = ViewModelProvider(this).get(AddPropertyViewModel::class.java)
        binding.viewModel = viewModel
        setupObservers()
        return binding.root
    }
    private fun setupObservers() {

        viewModel.addPropertyResponse.observe(viewLifecycleOwner) {

            if(it.error==false) {
                Toast.makeText(activity, "add property successful", Toast.LENGTH_LONG).show()
                binding.etAddress.text.clear()
                binding.etCity.text.clear()
                binding.etCountry.text.clear()
                binding.etState.text.clear()
                binding.etPurchasePrice.text.clear()
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