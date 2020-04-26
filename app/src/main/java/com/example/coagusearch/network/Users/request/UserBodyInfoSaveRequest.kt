package com.example.coagusearch.network.Users.request

data class UserBodyInfoSaveRequest(
    var name: String?,
    var surname: String?,
    var birthDay: Int?,
    var birthMonth: Int?,
    var birthYear: Int?,
    var height: Double?,
    var weight: Double?,
    var bloodType: String?,
    var rhType: String?,
    var gender: String?
)