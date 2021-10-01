package com.example.propertymanagementapp.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiService
import com.example.propertymanagementapp.api.response.GetPropertiesResponse
import com.example.propertymanagementapp.api.response.Property
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPropertiesViewModel(val apiService: ApiService): ViewModel() {
    val properties = ObservableField<List<Property>>()

    val error = ObservableField<String>()
    val loading = ObservableField<Boolean>()

    fun loadProperties(){
        error.set("")
        val call: Call<GetPropertiesResponse> = apiService.getProperties()

        call.enqueue(object: Callback<GetPropertiesResponse> {
            override fun onResponse(
                call: Call<GetPropertiesResponse>,
                response: Response<GetPropertiesResponse>
            ) {
                loading.set(false)

                if(!response.isSuccessful){
                    error.set("Error while loading properties. Error code: ${response.code()}")
                    return
                }

                val getPropertiesResponse = response.body()

                getPropertiesResponse?.let{
                    if(it.error != false){
                        error.set("Error while loading properties. Please retry.")
                        return
                    }
                    Log.d("PROPERTIES","${it.data}")
                    properties.set(it.data)
                }
            }

            override fun onFailure(call: Call<GetPropertiesResponse>, t: Throwable) {
                loading.set(false)
                error.set("Failed to load properties. Error: ${t.message}")
            }
        })
        loading.set(true)
    }
}