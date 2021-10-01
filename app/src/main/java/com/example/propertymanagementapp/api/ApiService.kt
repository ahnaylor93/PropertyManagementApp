package com.example.propertymanagementapp.api

import com.example.propertymanagementapp.api.request.LoginRequestData
import com.example.propertymanagementapp.api.request.RegisterRequestData
import com.example.propertymanagementapp.api.response.GetPropertiesResponse
import com.example.propertymanagementapp.api.response.LoginResponseData
import com.example.propertymanagementapp.api.response.RegisterResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-type: application/json")
    @POST("auth/login")
    fun login(
        @Body loginData: LoginRequestData
    ): Call<LoginResponseData>

    @POST("auth/register")
    fun register(
        @Body registerData: RegisterRequestData
    ): Call<RegisterResponseData>

    @GET("/property")
    fun getProperties(): Call<GetPropertiesResponse>
}