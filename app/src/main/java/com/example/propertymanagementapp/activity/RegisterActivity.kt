package com.example.propertymanagementapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.ApiService
import com.example.propertymanagementapp.api.request.RegisterRequestData
import com.example.propertymanagementapp.api.response.RegisterResponseData
import com.example.propertymanagementapp.databinding.ActivityRegisterBinding
import com.example.propertymanagementapp.viewmodel.RegisterViewModel
import retrofit2.Call
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.registerResponse.observe(this) {

            it.token?.let {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
                binding.etName.text.clear()
                binding.etEmail.text.clear()
                binding.etPassword.text.clear()
                binding.etConfirmPassword.text.clear()
            }

            it.error?.let {
                    hasError ->
                if(hasError) {
                    it?.message?.let {
                            msg ->
                        Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }
    }
}