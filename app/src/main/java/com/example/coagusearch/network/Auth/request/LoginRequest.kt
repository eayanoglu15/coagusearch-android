package com.example.coagusearch.network.Auth.request

data class LoginRequest(
    val identity_number: String,
    val password: String
)

