package com.example.coagusearch.network.Auth.request

data class SignUpRequest(
    val identity_number: String,
    val type: String,
    var name: String?,
    var surname: String?,
    var dateOfBirth: String?,
    var height: Double?,
    var weight: Double?,
    var bloodType: String?,
    var rhType: String?,
    var gender: String?
)