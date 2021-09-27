package com.example.propertymanagementapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler.sendEmptyMessageDelayed(LAUNCH_LOGIN_SCREEN,2000)
    }
    private val LAUNCH_LOGIN_SCREEN:Int = 200
    private val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what == LAUNCH_LOGIN_SCREEN){
                startActivity(Intent(baseContext, LoginRegisterChoiceActivity::class.java))
                finish()
            }
        }
    }
}