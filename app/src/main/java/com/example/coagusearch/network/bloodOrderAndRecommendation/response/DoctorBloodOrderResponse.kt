package com.example.coagusearch.network.bloodOrderAndRecommendation.response

import com.example.coagusearch.network.PatientData.response.SuggestionResponse


data class PreviousOrderResponse(
    val ordersList : List<DoctorBloodOrderResponse>

)


data class DoctorBloodOrderResponse (
    val bloodType: String? = null,
    val rhType: String? = null,
    val productType: String? = null,
    val quantity: Double,
    val additionalNote: String?,
    val kind: String?,
    val unit: String?
){

    fun getBloodTypeAsString():String{
        if(rhType.equals("Positive",true)){
            return bloodType+" Rh+"
        }
        else return bloodType+" Rh-"

    }
}

