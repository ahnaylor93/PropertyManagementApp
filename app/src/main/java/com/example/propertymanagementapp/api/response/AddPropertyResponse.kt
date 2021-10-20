package com.example.propertymanagementapp.api.response

import com.google.gson.annotations.SerializedName

data class AddPropertyResponse (
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: Property?
)