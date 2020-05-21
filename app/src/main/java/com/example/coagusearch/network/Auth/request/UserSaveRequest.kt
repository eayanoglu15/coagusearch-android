package com.example.coagusearch.network.Auth.request

data class UserSaveRequest(
    val identity_number: String,
    val type: String,
    var name: String?,
    var surname: String?,
    var birthDay : Int?,
    var birthMonth : Int?,
    var birthYear: Int?,
    var height: Double?,
    var weight: Double?,
    var bloodType: String?,
    var rhType: String?,
    var gender: String?,
    var doctor_identity_number : String?
)