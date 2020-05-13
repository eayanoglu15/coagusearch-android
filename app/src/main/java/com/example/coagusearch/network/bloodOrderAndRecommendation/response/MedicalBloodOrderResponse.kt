package com.example.coagusearch.network.bloodOrderAndRecommendation.response

data class MedicalBloodOrderResponse (
    val todoOrderList : List<DoctorBloodOrderResponse>,
    val doneOrderList : List<DoctorBloodOrderResponse>
)