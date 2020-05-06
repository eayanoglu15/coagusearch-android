package com.example.coagusearch.network.PatientData.response

import java.io.Serializable

data class UserBloodTestsResponse(
    val userTestList: List<UserBloodTestHistoryResponse>
):Serializable

data class UserBloodTestHistoryResponse(
    val id: Long,
    val testDate: BloodDateResponse
): Serializable
data class BloodDateResponse(
    val day: Int,
    val month: Int,
    val year: Int
): Serializable{
    fun getAsString():String{
        return day.toString()+"/"+month.toString()+"/"+year.toString()
    }
}
