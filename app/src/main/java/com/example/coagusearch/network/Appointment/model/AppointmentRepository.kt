package com.example.coagusearch.network.Appointment.model

import android.content.Context
import com.example.coagusearch.MainActivity
import com.example.coagusearch.NewAppointment
import com.example.coagusearch.network.Appointment.request.SaveAppointmentRequest
import com.example.coagusearch.network.Appointment.response.WeeklyAvailabilityResponse
import com.example.coagusearch.network.Auth.request.LoginRequest
import com.example.coagusearch.network.Auth.request.RefreshRequest
import com.example.coagusearch.network.Auth.request.SignUpRequest
import com.example.coagusearch.network.Auth.response.LoginResponse
import com.example.coagusearch.network.Auth.response.RefreshResponse
import com.example.coagusearch.network.Auth.response.SignUpResponse
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import kotlinx.android.synthetic.main.loginscreen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {
    fun availableTimeSlotsForAppointment(): WeeklyAvailabilityResponse? {
        var appointmentResponse: WeeklyAvailabilityResponse? = null
        retrofitClient.appointmentApi().availableTimeSlotsForAppointment()
            .enqueue(object : Callback<WeeklyAvailabilityResponse> {
                override fun onFailure(call: Call<WeeklyAvailabilityResponse>, t: Throwable) {
                   // (context as MainActivity).LoginButton.text ="Fail"
                }
                override fun onResponse(
                    call: Call<WeeklyAvailabilityResponse>,
                    response: Response<WeeklyAvailabilityResponse>
                ){
                    appointmentResponse = response.body()
                    println(appointmentResponse.toString())
                    (context as  MainActivity).showProgressLoading(false)
                }
            })
        return appointmentResponse

    }

    fun saveAppointmentForUser(saveAppointmentRequest: SaveAppointmentRequest): ApiResponse? {
        var apiResponse: ApiResponse? = null
        retrofitClient.appointmentApi().saveAppointmentForUser(saveAppointmentRequest)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    // (context as MainActivity).LoginButton.text ="Fail"

                }

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ){

                    apiResponse = response.body()
                }

            })
        return apiResponse

    }

}
