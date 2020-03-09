package com.example.coagusearch.network.Users.response

data class UserResponse(
    val identityNumber: String,
    val type: String,
    val userId: Long,
    var name: String?,
    var surname: String?,
    var dateOfBirth: String?,
    var height: Double?,
    var weight: Double?,
    var bloodType: String?,
    var rhType: String?,
    var gender: String?
)