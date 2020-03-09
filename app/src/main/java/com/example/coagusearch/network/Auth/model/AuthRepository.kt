package com.example.coagusearch.network.Auth.model

import android.content.Context
import androidx.core.content.edit
import com.example.coagusearch.MainActivity
import com.example.coagusearch.R
import com.example.coagusearch.network.ApiResult
import com.example.coagusearch.network.Auth.request.LoginRequest
import com.example.coagusearch.network.Auth.request.RefreshRequest
import com.example.coagusearch.network.Auth.request.SignUpRequest
import com.example.coagusearch.network.Auth.response.LoginResponse
import com.example.coagusearch.network.Auth.response.RefreshResponse
import com.example.coagusearch.network.Auth.response.SignUpResponse
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.typing.Ok
import com.example.coagusearch.typing.single
import com.google.gson.Gson
import kotlinx.android.synthetic.main.loginscreen.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class AuthRepository(private val context: Context) {
    private val retrofitClient: RetrofitClient = RetrofitClient()
    //TODO: Handle the Server error part which shows error on the screen
    fun signIn(identity_number: String, password: String): LoginResponse? {
        var loginResponse: LoginResponse? = null
        retrofitClient.authApi().signIn(LoginRequest(identity_number, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    (context as MainActivity).LoginButton.text ="Fail"

                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ){
                    (context as MainActivity).LoginButton.text =
                        response.body()?.tokenType.toString()
                        loginResponse = response.body()
                }

            })
        return loginResponse

    }

    fun signUp(signUpRequest: SignUpRequest): SignUpResponse? {
        var signUpResponse: SignUpResponse? = null
        retrofitClient.authApi().signUp(signUpRequest)
            .enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                   // (context as MainActivity).LoginButton.text ="Fail"

                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ){

                    signUpResponse = response.body()
                }

            })
        return signUpResponse

    }

    fun refresh(refreshToken: String): RefreshResponse? {
        var refreshResponse: RefreshResponse? = null
        retrofitClient.authApi().refresh(RefreshRequest(refreshToken))
            .enqueue(object : Callback<RefreshResponse> {
                override fun onFailure(call: Call<RefreshResponse>, t: Throwable) {
                    // (context as MainActivity).LoginButton.text ="Fail"

                }

                override fun onResponse(
                    call: Call<RefreshResponse>,
                    response: Response<RefreshResponse>
                ){

                    refreshResponse = response.body()
                }

            })
        return refreshResponse

    }
}
