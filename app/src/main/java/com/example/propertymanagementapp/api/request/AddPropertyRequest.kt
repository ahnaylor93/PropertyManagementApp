package com.example.propertymanagementapp.api.request

import com.google.gson.annotations.SerializedName

data class AddPropertyRequest(
    @SerializedName("propertyStatus")
    val propertyStatus: Boolean?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("userType")
    val userType: String?,
    @SerializedName("userId")
    val userId: String?,
)