package com.example.propertymanagementapp.api

import com.example.propertymanagementapp.api.request.AddPropertyRequest
import com.example.propertymanagementapp.api.request.AddTenantRequest
import com.example.propertymanagementapp.api.request.LoginRequestData
import com.example.propertymanagementapp.api.request.RegisterRequestData
import com.example.propertymanagementapp.api.response.*
import retrofit2.Call
import retrofit2.http.*

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

    @POST("property")
    fun addProperty(
        @Body propertyData: AddPropertyRequest
    ): Call<AddPropertyResponse>

    @GET("property")
    fun getProperties(): Call<GetPropertiesResponse>



    @GET("property/user/{id}")
    fun getPropertiesByUser(@Path("id") id: String): Call<GetPropertiesResponse>

    @POST("tenant")
    fun addTenant(
        @Body tenantData: AddTenantRequest
    ): Call<AddTenantResponse>

    @GET("tenant")
    fun getTenants(): Call<GetTenantResponse>

    ///API doesn't work properly in logic dont use it
    @GET("tenant/landlord/{id}")
    fun getTenantsByLandlord(@Path("id") id:String): Call<GetTenantResponse>
}