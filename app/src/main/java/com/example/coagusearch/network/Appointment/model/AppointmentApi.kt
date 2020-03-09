package com.example.coagusearch.network.Appointment.model

import com.example.coagusearch.network.Appointment.request.SaveAppointmentRequest
import com.example.coagusearch.network.Appointment.response.WeeklyAvailabilityResponse
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppointmentApi {

    @GET("/appointment/getByUser")
    fun availableTimeSlotsForAppointment(): Call<WeeklyAvailabilityResponse>
    @POST("/appointment/save")
    fun saveAppointmentForUser(@Body saveAppointmentRequest: SaveAppointmentRequest): Call<ApiResponse>

}