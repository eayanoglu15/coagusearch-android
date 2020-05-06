package com.example.coagusearch.network.bloodOrderAndRecommendation.response

import java.io.Serializable

data class UserBloodOrderResponse (
    val bloodType: String? = null,
    val rhType: String? = null,
    val productType: String? = null,
    val quantity: Double,
    val additionalNote: String?,
    val bloodTestId:Long?,
    val kind: String?,
    val unit: String?
):Serializable