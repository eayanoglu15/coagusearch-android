package com.example.coagusearch.network.bloodOrderAndRecommendation.request

data class OrderForUserDataRequest(
    val unit: String,
    val kind: String,
    val quantity: Double,
    val product: String,
    val bloodTestId: Long
)
