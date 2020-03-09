package com.example.coagusearch.network.Auth.response

data class LoginResponse(
    val accessToken: String,
    val tokenType: String,
    val refreshToken: String
)
