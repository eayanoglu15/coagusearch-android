package com.example.coagusearch.network.PatientData.model

import com.example.coagusearch.network.Appointment.request.DeleteAppointmentsForUserRequest
import com.example.coagusearch.network.Appointment.request.SaveAppointmentRequest
import com.example.coagusearch.network.Appointment.response.UserAppointmentResponse
import com.example.coagusearch.network.Appointment.response.WeeklyAvailabilityResponse
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestDataRequest
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestRequest
import com.example.coagusearch.network.PatientData.response.SuggestionListResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestHistoryResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestsResponse
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface PatientDataApi {

    @Headers("RequireAuth:1")
    @POST("/patientData/getAllBloodTest")
    fun getPatientBloodTestRequest(@Body patientId: GetPatientBloodTestRequest): Call<UserBloodTestsResponse>

    @Headers("RequireAuth:1")
    @POST("/patientData/getLastOfPatient")
    fun getPatientBloodTestRequestLast(@Body patientId: GetPatientBloodTestRequest): Call<UserBloodTestDataResponse>

    @Headers("RequireAuth:1")
    @POST("/patientData/getPatientBloodDataById")
    fun getPatientBloodDataById(@Body bloodTestDataId: GetPatientBloodTestDataRequest): Call<UserBloodTestDataResponse>

    @Headers("RequireAuth:1")
    @POST("/patientData/getSuggestionOfBloodTest")
    fun getSuggestionOfBloodTest(@Body bloodTestDataId: GetPatientBloodTestDataRequest): Call<SuggestionListResponse>
}