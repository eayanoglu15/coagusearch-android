package com.example.coagusearch.network.Auth.response

data class RefreshResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)