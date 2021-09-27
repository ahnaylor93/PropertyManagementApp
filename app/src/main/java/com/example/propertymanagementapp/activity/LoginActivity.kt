package com.example.propertymanagementapp.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.databinding.ActivityLoginBinding
import com.example.propertymanagementapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        setupEvents()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.processing.observe(this) {
            if(it == true) {
                binding.pbProcessing.visibility = View.VISIBLE
            } else {
                binding.pbProcessing.visibility = View.GONE
            }
        }

        viewModel.loginResponse.observe(this) { response ->

            response.token?.let {
                Toast.makeText(baseContext, "Login successful", Toast.LENGTH_LONG).show()
                val sharedPref = getSharedPreferences("app_settings",
                    AppCompatActivity.MODE_PRIVATE
                )
                val editor: SharedPreferences.Editor = sharedPref.edit()
                val name = response.user?.name
                val userId = response.user?._id
                val email = response.user?.email
                val type = response.user?.type

                editor.putString("name", name)
                editor.putString("userId", userId)
                editor.putString("email", email)
                editor.putString("type", type)
                editor.apply()
                startActivity(Intent(baseContext, HomeActivity::class.java))
            }

            response.error?.let {
                    hasError ->
                if(hasError) {
                    response?.message?.let {
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

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(email, password)
        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

}