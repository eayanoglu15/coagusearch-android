package com.example.coagusearch.network.Users.model

import com.example.coagusearch.network.Users.request.AmbulancePatientRequest
import com.example.coagusearch.network.Users.request.PatientBodyInfoSaveRequest
import com.example.coagusearch.network.Users.request.PatientDetailRequest
import com.example.coagusearch.network.Users.request.UserBodyInfoSaveRequest
import com.example.coagusearch.network.Users.response.DoctorMainScreenResponse
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.Users.response.PatientMainScreenResponse
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersApi {
    @Headers("RequireAuth:1")
    @GET("/users/me")
    fun getUserInfo(): Call<UserResponse>

    @Headers("RequireAuth:1")
    @GET("/users/getMyPatients")
    fun getMyPatient(): Call<List<UserResponse>>

    @Headers("RequireAuth:1")
    @GET("/users/getPatientMainScreen")
    fun getPatientMainScreen(): Call<PatientMainScreenResponse>

    @Headers("RequireAuth:1")
    @POST("/users/saveBodyInfo")
    fun saveBodyInfo(@Body userBodyInfoSaveRequest: UserBodyInfoSaveRequest): Call<ApiResponse>

    @Headers("RequireAuth:1")
    @GET("/users/getDoctorMainScreen")
    fun getDoctorMainScreen(): Call<DoctorMainScreenResponse>

    @Headers("RequireAuth:1")
    @GET("/users/getMyPatients")
    fun getMyPatients(): Call<List<UserResponse>>


    @Headers("RequireAuth:1")
    @POST("/users/getPatientDetail")
    fun getPatientDetail(@Body patientDetailRequest: PatientDetailRequest): Call<PatientDetailResponse>


    @Headers("RequireAuth:1")
    @POST("/users/saveBodyInfoOfPatient")
    fun saveBodyInfoOfPatient(@Body patientDetailRequest: PatientBodyInfoSaveRequest): Call<ApiResponse>



    @Headers("RequireAuth:1")
    @POST("/users/saveAmbulancePatient")
    fun saveAmbulancePatient(@Body userID: AmbulancePatientRequest): Call<ApiResponse>

}