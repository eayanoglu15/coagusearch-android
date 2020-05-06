package com.example.coagusearch.network.PatientData.response

import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse


data class UserBloodTestDataResponse(
    val bloodTestId: Long,
    val userBloodData: List<UserBloodTestDataSpesificResponse>,
    val ordersOfData : List<DoctorBloodOrderResponse>
)

data class UserBloodTestDataSpesificResponse(
    val testName: String,
    val categoryList: List<UserBloodTestDataCategoryResponse>

)

data class UserBloodTestDataCategoryResponse(
    val categoryName: String,
    val maximumValue: Double,
    val minimumValue: Double,
    val optimalMaximumValue: Double,
    val optimalMinimumValue: Double,
    val value: Double
){
    fun ishigh():Boolean{
        if(value>optimalMaximumValue)
            return true
        else
            return false
    }
    fun isLow():Boolean{
        if(value<optimalMinimumValue)
            return true
        else
            return false
    }
    fun startWeight():Double{
        return (optimalMinimumValue-minimumValue)/(maximumValue-minimumValue)
    }
    fun midWeight():Double{
        return (optimalMaximumValue-optimalMinimumValue)/(maximumValue-minimumValue)
    }
    fun endWeight():Double{
        return (maximumValue-optimalMaximumValue)/(maximumValue-minimumValue)
    }
    fun valueBias():Double{
        return (value-minimumValue)/(maximumValue-minimumValue)
    }

}
