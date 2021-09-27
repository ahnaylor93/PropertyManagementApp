package com.example.propertymanagementapp.api

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private lateinit var myRetrofit: Retrofit

    fun getRetrofit(): Retrofit{
        if(!this::myRetrofit.isInitialized){
            myRetrofit = Retrofit.Builder()
                .baseUrl("https://apolis-property-management.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return myRetrofit
    }

    val apiService: ApiService by lazy{
        getRetrofit()
        myRetrofit.create(ApiService::class.java)
    }
}