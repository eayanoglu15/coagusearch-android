package com.example.coagusearch.network.notifications.model

import com.example.coagusearch.network.notifications.request.CallPatientRequest
import com.example.coagusearch.network.notifications.request.NotifyMedicalRequest
import com.example.coagusearch.network.notifications.response.NotificationResponse
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST





interface NotificationsApi {

    @Headers("RequireAuth:1")
    @POST("/notification/callPatient")
    fun callPatientForAppointment(@Body patientId:CallPatientRequest): Call<ApiResponse>


    @Headers("RequireAuth:1")
    @POST("/notification/notify-medical")
    fun notifyMedicalTeam(@Body patientId:NotifyMedicalRequest): Call<ApiResponse>


    @Headers("RequireAuth:1")
    @GET("/notification/page")
    fun getPage(): Call<List<NotificationResponse>>

}