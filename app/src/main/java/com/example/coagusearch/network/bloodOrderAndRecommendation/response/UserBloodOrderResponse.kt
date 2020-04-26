package com.example.coagusearch.network.bloodOrderAndRecommendation.response

data class UserBloodOrderResponse (
    val bloodType: String? = null,
    val rhType: String? = null,
    val productType: String? = null,
    val unit: Int,
    val additionalNote: String?
)