package com.example.propertymanagementapp.viewmodel

import android.widget.Toast
import androidx.databinding.ObservableField
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
    val processing = ObservableField<Boolean>()

    val name = ObservableField<String>("")
    val nameError = ObservableField<String>()
    val email = ObservableField<String>("")
    val emailError = ObservableField<String>()
    val password = ObservableField<String>("")
    val passwordError = ObservableField<String>()
    val confirmPassword = ObservableField<String>("")
    val confirmPasswordError = ObservableField<String>()


    fun register() {
        var hasError = false
        name.get()?.let{
            if(it.isEmpty()){
                nameError.set("The Name field can not be empty.")
                hasError = true
            }else{
                nameError.set(null)
            }
        }
        email.get()?.let{
            if(it.isEmpty()) {
                emailError.set("The Email field can not be empty.")
                hasError = true
            }else{
                emailError.set(null)
            }
        }
        password.get()?.let{
            if(it.isEmpty()){
                passwordError.set("The Password field can not be empty.")
                hasError = true
            }
            else if(it.length < 6){
                passwordError.set("Your password must be at least six characters long.")
                hasError = true
            }else{
                passwordError.set(null)
            }

            if(it != confirmPassword.get()){
                confirmPasswordError.set("Password and Confirm Password must match.")
                hasError = true
            }else{
                confirmPasswordError.set(null)
            }
        }

        if(hasError){
            return
        }

        val type = "landlord"
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val createdAt = formatter.format(date)


        val registerRequest = RegisterRequestData(
            email.get()?:"NA",
            password.get()?:"NA",
            name.get()?:"NA",
            type,
            createdAt)
        val call: Call<RegisterResponseData> = ApiClient.apiService.register(registerRequest)

        call.enqueue(object : Callback<RegisterResponseData> {
            override fun onResponse(
                call: Call<RegisterResponseData>,
                response: Response<RegisterResponseData>
            ) {
                processing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to register. Error code: ${response.code()}")
                    return
                }
                registerResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<RegisterResponseData>, t: Throwable) {
                processing.set(false)
                error.postValue("Failed to register. Error: ${t.message}")
            }
        })
        processing.set(true)
    }
}