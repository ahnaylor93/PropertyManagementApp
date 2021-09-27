package com.example.propertymanagementapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.request.RegisterRequestData
import com.example.propertymanagementapp.api.response.RegisterResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel: ViewModel() {

    val registerResponse = MutableLiveData<RegisterResponseData>()
    val error = MutableLiveData<String>()
    val processing = MutableLiveData<Boolean>()

    fun register(email: String, password: String, name: String, type: String, createdAt: String) {
        val registerRequest = RegisterRequestData(email, password, name, type, createdAt,)
        val call: Call<RegisterResponseData> = ApiClient.apiService.register(registerRequest)

        call.enqueue(object : Callback<RegisterResponseData> {
            override fun onResponse(
                call: Call<RegisterResponseData>,
                response: Response<RegisterResponseData>
            ) {
                processing.postValue(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to register. Error code: ${response.code()}")
                    return
                }
                registerResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<RegisterResponseData>, t: Throwable) {
                processing.postValue(false)
                error.postValue("Failed to register. Error: ${t.toString()}")
            }
        })
        processing.postValue(true)
    }
}