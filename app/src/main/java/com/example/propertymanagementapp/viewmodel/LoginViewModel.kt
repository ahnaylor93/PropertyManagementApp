package com.example.propertymanagementapp.viewmodel

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.request.LoginRequestData
import com.example.propertymanagementapp.api.response.LoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    val emailId = ObservableField<String>("")
    val password = ObservableField<String>("")
    val emailIdError = ObservableField<String>()
    val passwordError = ObservableField<String>()

    val loginResponse = MutableLiveData<LoginResponseData>()
    val error = MutableLiveData<String>()
    val processing = ObservableField<Boolean>()

    fun login() {
        var hasError = false
        emailId.get()?.let {
            if(it.isEmpty()) {
                emailIdError.set("Please enter email id")
                hasError = true
            } else {
                emailIdError.set(null)
            }
        }

        password.get()?.let {
            if(it.isEmpty()) {
                passwordError.set("Please enter password")
                hasError = true
            } else {
                passwordError.set(null)
            }
        }

        if(hasError) {
            return
        }

        val loginRequest = LoginRequestData(emailId.get()?:"NA", password.get()?:"NA")

        val call: Call<LoginResponseData> = ApiClient.apiService.login(loginRequest)

        call.enqueue(object : Callback<LoginResponseData> {
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {
                processing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to authenticate. Error code: ${response.code()}")
                    return
                }
                loginResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                processing.set(false)
                error.postValue("Failed to authenticate. Error is : ${t.toString()}")
            }
        })

        processing.set(true)
    }
}