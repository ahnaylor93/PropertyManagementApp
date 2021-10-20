package com.example.propertymanagementapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
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

        val call: Call<GetPropertiesResponse> = apiService.getProperties()
        load(call)
    }
    fun loadPropertiesByUser(id:String){
        val call: Call<GetPropertiesResponse> = apiService.getPropertiesByUser(id)
        load(call)
    }
    fun load(call:Call<GetPropertiesResponse>){
        Log.d("PROPERTIES","started loading")
        error.set("")
        call.enqueue(object: Callback<GetPropertiesResponse> {
            override fun onResponse(
                call: Call<GetPropertiesResponse>,
                response: Response<GetPropertiesResponse>
            ) {
                loading.set(false)
                Log.d("PROPERTIES","${response.body()}")
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
                Log.e("fail", t.getLocalizedMessage());
                t.printStackTrace();
            }
        })
        //Log.d("PROPERTIES","finish loading")
        loading.set(true)
    }

}
