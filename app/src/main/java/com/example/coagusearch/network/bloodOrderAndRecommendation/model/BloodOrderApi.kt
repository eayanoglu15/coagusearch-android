package com.example.coagusearch.network.bloodOrderAndRecommendation.model

import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderIDRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.OrderForUserDataRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.MedicalBloodOrderResponse
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BloodOrderApi {

    @Headers("RequireAuth:1")
    @POST("/blood/order")
    fun bloodOrderRequest(@Body order: BloodOrderRequest): Call<ApiResponse>

    @Headers("RequireAuth:1")
    @POST("/blood/orderForUserData")
    fun bloodOrderForUserData(@Body order: OrderForUserDataRequest): Call<ApiResponse>

    @Headers("RequireAuth:1")
    @GET("/blood/previousOrders")
    fun previousOrders(): Call<List<DoctorBloodOrderResponse>>

    @Headers("RequireAuth:1")
    @POST("/blood/setReadyOrder")
    fun setOrderReady(@Body order: BloodOrderIDRequest): Call<MedicalBloodOrderResponse>
    @Headers("RequireAuth:1")
    @GET("/blood/getOrdersForMedical")
    fun getOrdersForMedical(): Call<MedicalBloodOrderResponse>

}