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

        /*
        FOR TESTING PURPOSES, THIS SCREEN WILL IMMEDIATELY REDIRECT TO
        THE PROPERTIES ACTIVITY UNTIL PROPER HOMESCREEN UI AND FUNCTIONALITY
        HAS BEEN ADDED
         */
        startActivity(Intent(baseContext, PropertyActivity::class.java))
    }
}