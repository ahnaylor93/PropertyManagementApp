package com.example.propertymanagementapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        setupEvents()

        setupObservers()
    }

    private fun setupObservers() {

        viewModel.registerResponse.observe(this) {

            it.token?.let {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
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

    private fun setupEvents() {

        binding.btnRegister.setOnClickListener {
            if(validateInput()) {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val name = binding.etName.text.toString()
                val type = "landlord"

                val date = Calendar.getInstance().time
                val formatter = SimpleDateFormat.getDateTimeInstance()
                val createdAt = formatter.format(date)

                viewModel.register(email, password, name, type, createdAt)
            }
        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    fun validateInput(): Boolean{
        var valid = true

        if(binding.etName.text.toString().trim() == ""){
            Toast.makeText(baseContext, "The Name field can not be empty.", Toast.LENGTH_LONG).show()
            valid = false
        }
        if(binding.etEmail.text.toString().trim() == ""){
            Toast.makeText(baseContext, "The Email field can not be empty.", Toast.LENGTH_LONG).show()
            valid = false
        }
        if(binding.etPassword.text.toString().trim() == ""){
            Toast.makeText(baseContext, "The Password field can not be empty.", Toast.LENGTH_LONG).show()
            valid = false
        }
        if(binding.etPassword.text.toString().trim().length < 6){
            Toast.makeText(baseContext, "Your password must be at least six characters long.", Toast.LENGTH_LONG).show()
            valid = false
        }
        if(binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()){
            Toast.makeText(baseContext, "Password and Confirm Password must match.", Toast.LENGTH_LONG).show()
            valid = false
        }
        return valid
    }

}