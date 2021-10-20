package com.example.propertymanagementapp.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.request.AddPropertyRequest
import com.example.propertymanagementapp.api.request.AddTenantRequest
import com.example.propertymanagementapp.api.response.AddPropertyResponse
import com.example.propertymanagementapp.api.response.AddTenantResponse
import com.example.propertymanagementapp.databinding.FragmentAddTenantBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTenantViewModel(propertyId:String?,binding:FragmentAddTenantBinding):ViewModel() {
    val addTenantResponse = MutableLiveData<AddTenantResponse>()
    val error = MutableLiveData<String>()
    val processing = ObservableField<Boolean>()

    val email = ObservableField<String>("")
    val emailError = ObservableField<String>()
    val name = ObservableField<String>("")
    val nameError = ObservableField<String>()

    val propertyId= propertyId
    val binding=binding

    fun addTenant(){
        name.set(binding.etName.text.toString())
        email.set(binding.etEmail.text.toString())
        var hasError = false
        //Log.d("addpro","1$hasError")
        name.get()?.let{
            if(it.isEmpty()){
                nameError.set("The name field can not be empty.")
                hasError = true
            }else{
                nameError.set(null)
            }
        }
        //Log.d("addpro","2$hasError")
        email.get()?.let{
            if(it.isEmpty()) {
                emailError.set("The email field can not be empty.")
                hasError = true
            }else{
                emailError.set(null)
            }
        }

        if(hasError){
            return
        }

        val addTenantRequest = AddTenantRequest(
            email.get()?:"NA",
            name.get()?:"NA",
            propertyId)
        val call: Call<AddTenantResponse> = ApiClient.apiService.addTenant(addTenantRequest)
        call.enqueue(object : Callback<AddTenantResponse> {
            override fun onResponse(
                call: Call<AddTenantResponse>,
                response: Response<AddTenantResponse>
            ) {

                processing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to add Tenant. Error code: ${response.code()}")
                    return
                }
                addTenantResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<AddTenantResponse>, t: Throwable) {
                processing.set(false)
                error.postValue("Failed to add Tenant. Error: ${t.message}")
                t.printStackTrace();
            }
        })
        processing.set(true)
    }
}