package com.example.coagusearch.api

import com.example.coagusearch.entity.Auth
import com.example.coagusearch.entity.LoginRequest
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/sign-in")
    fun signIn(@Body loginModel: LoginRequest): Call<Auth>
}