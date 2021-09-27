package com.example.propertymanagementapp.viewmodel

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.request.LoginRequestData
import com.example.propertymanagementapp.api.response.LoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    val loginResponse = MutableLiveData<LoginResponseData>()
    val error = MutableLiveData<String>()
    val processing = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        val loginRequest = LoginRequestData(email, password)
        val call: Call<LoginResponseData> = ApiClient.apiService.login(loginRequest)

        call.enqueue(object : Callback<LoginResponseData> {
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {
                processing.postValue(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to authenticate. Error code: ${response.code()}")
                    return
                }
                loginResponse.postValue(response.body())

            }
            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                processing.postValue(false)
                error.postValue("Failed to authenticate. Error is : ${t.toString()}")
            }
        })
        processing.postValue(true)
    }
}