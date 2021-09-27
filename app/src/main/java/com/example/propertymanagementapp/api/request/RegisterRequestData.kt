package com.example.propertymanagementapp.api.request

data class RegisterRequestData(
    val email: String,
    val password: String,
    val name: String,
    val type: String,
    val createdAt: String
)
