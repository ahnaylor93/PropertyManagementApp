package com.example.propertymanagementapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagementapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
    }
    fun setupEvents(){
        binding.btnAlerts.setOnClickListener(){

        }
        binding.btnProperties.setOnClickListener(){
            startActivity(Intent(baseContext,PropertyScreenActivity::class.java))
        }
    }
}