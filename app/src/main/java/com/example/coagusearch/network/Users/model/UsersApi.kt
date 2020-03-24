package com.example.coagusearch.network.Users.model

import com.example.coagusearch.network.Users.request.UserBodyInfoSaveRequest
import com.example.coagusearch.network.Users.response.PatientMainScreenResponse
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.http.*

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


}