package com.example.coagusearch.network.Auth.model

import com.example.coagusearch.network.Auth.request.LoginRequest
import com.example.coagusearch.network.Auth.request.RefreshRequest
import com.example.coagusearch.network.Auth.request.SignUpRequest
import com.example.coagusearch.network.Auth.request.UserSaveRequest
import com.example.coagusearch.network.Auth.response.LoginResponse
import com.example.coagusearch.network.Auth.response.RefreshResponse
import com.example.coagusearch.network.Auth.response.SignUpResponse
import com.example.coagusearch.network.Auth.response.UserSaveResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/sign-in")
    fun signIn(@Body loginModel: LoginRequest): Call<LoginResponse>

    @POST("/auth/sign-up")
    fun signUp(@Body signUpModel: SignUpRequest): Call<SignUpResponse>

    @POST("/auth/refresh")
    fun refresh(@Body authRefreshRequest: RefreshRequest): Call<RefreshResponse>

    @Headers("RequireAuth:1")
    @POST("/auth/savePatient")
    fun savePatient(@Body request: UserSaveRequest): Call<UserSaveResponse>
}