package com.example.coagusearch.network.bloodOrderAndRecommendation.response


data class PreviousOrderResponse(
    val ordersList : List<DoctorBloodOrderResponse>

)


data class DoctorBloodOrderResponse (
    val bloodType: String? = null,
    val rhType: String? = null,
    val productType: String? = null,
    val quantity: Double,
    val additionalNote: String?,
    val patientName: String? = null,
    val patientSurname : String? = null,
    val bloodTestId:Long?,
    val kind: String?,
    val unit: String?,
    val isReady : Boolean,
    val bloodOrderId: Long
){

    fun getBloodTypeAsString():String{
        if(rhType.equals("Positive",true)){
            return bloodType+" Rh+"
        }
        else return bloodType+" Rh-"

    }
}

