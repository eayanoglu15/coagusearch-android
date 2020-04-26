package com.example.coagusearch.network.bloodOrderAndRecommendation.request

data class BloodOrderRequest (
    val bloodType: String,
    val rhType: String,
    val patientId: Long?,
    val productType: String,
    val unit: Int,
    val additionalNote: String?
)
