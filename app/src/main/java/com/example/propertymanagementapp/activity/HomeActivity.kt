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
            startActivity(Intent(baseContext,PropertyActivity::class.java))
        }
        binding.btnTenants.setOnClickListener(){
            startActivity(Intent(baseContext,TenantsScreenActivity::class.java))
        }
        binding.btnTransaction.setOnClickListener(){
            startActivity(Intent(baseContext,TransactionsScreenActivity::class.java))
        }
        binding.btnCollectRent.setOnClickListener(){
            startActivity(Intent(baseContext,CollectRentScreenActivity::class.java))
        }
        binding.btnToDo.setOnClickListener(){
            startActivity(Intent(baseContext,ToDoListScreenActivity::class.java))
        }
    }
}