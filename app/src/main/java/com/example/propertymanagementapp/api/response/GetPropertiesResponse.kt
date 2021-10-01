package com.example.propertymanagementapp.api.response

data class GetPropertiesResponse(
    val error: Boolean?,
    val message: String?,
    val count: Int?,
    val data: List<Property>
)

data class Property(
    val propertyStatus: Boolean?,
    val mortageInfo: Boolean?,
    val image: String?,
    val _id : String?,
    val purchasePrice: Double?,
    val address: String?,
    val state: String?,
    val city: String?,
    val country: String?,
    val userType: String?,
    val __v: Int?,
    val latitude: String?,
    val longitude: String?,
    val userId: String?
)