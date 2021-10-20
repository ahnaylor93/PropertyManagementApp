package com.example.propertymanagementapp.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiService
import com.example.propertymanagementapp.api.response.GetPropertiesResponse
import com.example.propertymanagementapp.api.response.GetTenantResponse
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.api.response.Tenant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetTenantsViewModel(val apiService: ApiService):ViewModel() {
    val tenants = ObservableField<List<Tenant>>()
    val error = ObservableField<String>()
    val loading = ObservableField<Boolean>()

    fun getTenant(){
        val call: Call<GetTenantResponse> = apiService.getTenants()
        load(call)
    }
    fun getTenantsByLandlord(landlordId:String){
        val call: Call<GetTenantResponse> = apiService.getTenantsByLandlord(landlordId)
        load(call)
    }
    fun load(call:Call<GetTenantResponse>){
        error.set("")
        call.enqueue(object: Callback<GetTenantResponse> {
            override fun onResponse(
                call: Call<GetTenantResponse>,
                response: Response<GetTenantResponse>
            ) {
                loading.set(false)

                if(!response.isSuccessful){
                    error.set("Error while loading tenants. Error code: ${response.code()}")
                    return
                }

                val getTenantsResponse = response.body()

                getTenantsResponse?.let{
                    if(it.error != false){
                        error.set("Error while loading tenants. Please retry.")
                        return
                    }
                    tenants.set(it.data)
                }
            }

            override fun onFailure(call: Call<GetTenantResponse>, t: Throwable) {
                loading.set(false)
                error.set("Failed to load tenants. Error: ${t.message}")
                Log.e("fail", t.getLocalizedMessage());
                t.printStackTrace();
            }
        })
        //Log.d("PROPERTIES","finish loading")
        loading.set(true)
    }
}