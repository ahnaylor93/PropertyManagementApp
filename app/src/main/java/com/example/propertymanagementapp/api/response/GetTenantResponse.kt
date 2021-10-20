package com.example.propertymanagementapp.api.response

import com.google.gson.annotations.SerializedName

data class GetTenantResponse (
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("data")
    val data: List<Tenant>
)

data class Tenant(
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email : String?,
    @SerializedName("propertyId")
    val propertyId: String?
)