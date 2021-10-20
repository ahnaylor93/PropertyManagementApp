package com.example.propertymanagementapp.api.response

import com.google.gson.annotations.SerializedName

data class GetPropertiesResponse(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val data: List<Property>
)

data class Property(
    @SerializedName("propertyStatus")
    val propertyStatus: Boolean?,
    @SerializedName("mortageInfo")
    val mortageInfo: Boolean?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("_id")
    val _id : String?,
    @SerializedName("purchasePrice")
    val purchasePrice: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("userType")
    val userType: String?,
    @SerializedName("__v")
    val __v: Int?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("userId")
    val userId: String?
)