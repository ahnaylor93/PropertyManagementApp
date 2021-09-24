package com.example.propertymanagementapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagementapp.databinding.ActivityLoginRegisterChoiceBinding

class LoginRegisterChoiceActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginRegisterChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(baseContext, RegisterActivity::class.java))
        }
    }
}