package com.example.propertymanagementapp.viewmodel

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.api.ApiClient
import com.example.propertymanagementapp.api.request.AddPropertyRequest
import com.example.propertymanagementapp.api.request.RegisterRequestData
import com.example.propertymanagementapp.api.response.AddPropertyResponse
import com.example.propertymanagementapp.api.response.RegisterResponseData
import com.example.propertymanagementapp.databinding.FragmentAddPropertyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPropertyViewModel(userId:String?,userType:String?,binding: FragmentAddPropertyBinding):ViewModel() {
    val addPropertyResponse = MutableLiveData<AddPropertyResponse>()
    val error = MutableLiveData<String>()
    val processing = ObservableField<Boolean>()

    val address = ObservableField<String>("")
    val addressError = ObservableField<String>()
    val city = ObservableField<String>("")
    val cityError = ObservableField<String>()
    val state = ObservableField<String>("")
    val stateError = ObservableField<String>()
    val country = ObservableField<String>("")
    val countryError = ObservableField<String>()
    val price = ObservableField<Double>(0.0)
    val priceError = ObservableField<String>()
    val userId=userId
    val userType=userType
    val binding=binding
    fun addProperty() {
        address.set(binding.etAddress.text.toString())
        city.set(binding.etCity.text.toString())
        state.set(binding.etState.text.toString())
        country.set(binding.etCountry.text.toString())
        price.set(binding.etPurchasePrice.text.toString().toDouble())
        Log.d("addpro","reaction")
        var hasError = false
        Log.d("addpro","1$hasError")
        address.get()?.let{
            if(it.isEmpty()){
                addressError.set("The address field can not be empty.")
                hasError = true
            }else{
                addressError.set(null)
            }
        }
        Log.d("addpro","2$hasError")
        city.get()?.let{
            if(it.isEmpty()) {
                cityError.set("The city field can not be empty.")
                hasError = true
            }else{
                cityError.set(null)
            }
        }
        state.get()?.let{
            if(it.isEmpty()){
                stateError.set("The state field can not be empty.")
                hasError = true
            }
            else{
                stateError.set(null)
            }
        }
        country.get()?.let{
            if(it.isEmpty()){
                countryError.set("The country field can not be empty.")
                hasError = true
            }
            else{
                countryError.set(null)
            }
        }
        price.get()?.let{
            if(it==null){
                priceError.set("The price field can not be empty.")
                hasError = true
            }
            else if(it<0){
                priceError.set("The price can not be negative")
                hasError = true
            }
            else{
                priceError.set(null)
            }
        }
        Log.d("addpro","0$hasError")
        if(hasError){
            return
        }

        val addPropertyRequest = AddPropertyRequest(
            true,
            address.get()?:"NA",
            city.get()?:"NA",
            state.get()?:"NA",
            country.get()?:"NA",
            price.get()?:0.0,
            userType,
            userId)
        val call: Call<AddPropertyResponse> = ApiClient.apiService.addProperty(addPropertyRequest)
        call.enqueue(object : Callback<AddPropertyResponse> {
            override fun onResponse(
                call: Call<AddPropertyResponse>,
                response: Response<AddPropertyResponse>
            ) {

                processing.set(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to add property. Error code: ${response.code()}")
                    return
                }
                addPropertyResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<AddPropertyResponse>, t: Throwable) {
                processing.set(false)
                error.postValue("Failed to add property. Error: ${t.message}")
                t.printStackTrace();
            }
        })
        processing.set(true)
    }
}