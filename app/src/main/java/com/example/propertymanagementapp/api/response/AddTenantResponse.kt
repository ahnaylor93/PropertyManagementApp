package com.example.propertymanagementapp.api.response

import com.google.gson.annotations.SerializedName

data class AddTenantResponse (
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: Data?
    )

class Data(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("_v")
    val _v: Int?
)
