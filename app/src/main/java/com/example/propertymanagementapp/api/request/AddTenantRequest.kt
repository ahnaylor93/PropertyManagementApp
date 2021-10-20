package com.example.propertymanagementapp.api.request

import com.google.gson.annotations.SerializedName

data class AddTenantRequest(
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("propertyId")
    val propertyId: String?
)