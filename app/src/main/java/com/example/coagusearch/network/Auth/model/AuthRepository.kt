package com.example.coagusearch.network.Auth.model

import android.content.Context
import androidx.core.content.edit
import com.example.coagusearch.Constants
import com.example.coagusearch.MainActivity
import com.example.coagusearch.R
import com.example.coagusearch.network.ApiResult
import com.example.coagusearch.network.Auth.request.LoginRequest
import com.example.coagusearch.network.Auth.request.RefreshRequest
import com.example.coagusearch.network.Auth.request.SignUpRequest
import com.example.coagusearch.network.Auth.response.LoginResponse
import com.example.coagusearch.network.Auth.response.RefreshResponse
import com.example.coagusearch.network.Auth.response.SignUpResponse
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.typing.Ok
import com.example.coagusearch.typing.single
import com.example.coagusearch.utils.PreferenceHelper
import com.example.coagusearch.utils.securelyGetString
import com.example.coagusearch.utils.securelyPutString
import com.google.gson.Gson
import kotlinx.android.synthetic.main.loginscreen.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class AuthRepository(
    private val context: Context,
    private val authInterceptor: AuthInterceptor,
    private val retrofitClient: RetrofitClient
) {
    init {
        authInterceptor.initAuthRepository(this)
    }

    //TODO: Handle the Server error part which shows error on the screen
    fun signIn(identity_number: String, password: String): LoginResponse? {
        var loginResponse: LoginResponse? = null
        retrofitClient.authApi().signIn(LoginRequest(identity_number, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    //(context as MainActivity).LoginButton.text = "Fail"

                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val auth = response.body() as LoginResponse
                    val sharedPref = PreferenceHelper.defaultPrefs(context)

                    sharedPref.securelyPutString(context, Constants.SHARED_PREF_ACCESS_TOKEN, auth.accessToken
                        ?: "")
                    sharedPref.securelyPutString(context, Constants.SHARED_PREF_REFRESH_TOKEN, auth.refreshToken
                        ?: "")
                    sharedPref.securelyPutString(context, Constants.SHARED_PREF_TOKEN_TYPE, auth.tokenType
                        ?: "")
                    //(context as MainActivity).LoginButton.text =
                     //   response.body()?.tokenType.toString()
                    loginResponse = response.body()
                    println(sharedPref.securelyGetString(context, Constants.SHARED_PREF_ACCESS_TOKEN))
                    println(sharedPref.securelyGetString(context, Constants.SHARED_PREF_REFRESH_TOKEN))
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
                ) {

                    signUpResponse = response.body()
                }

            })
        return signUpResponse

    }

    fun refresh(refreshToken: String): Int {
        var refreshResponse: Int = 200
        println( "Refresh called")
        retrofitClient.authApi().refresh(RefreshRequest(refreshToken))
            .enqueue(object : Callback<RefreshResponse> {
                override fun onFailure(call: Call<RefreshResponse>, t: Throwable) {
                    // (context as MainActivity).LoginButton.text ="Fail"

                }

                override fun onResponse(
                    call: Call<RefreshResponse>,
                    response: Response<RefreshResponse>
                ) {
                    val authRefreshResponse = response.body() as RefreshResponse
                    val sharedPref = PreferenceHelper.defaultPrefs(context)

                    sharedPref.securelyPutString(
                        context, Constants.SHARED_PREF_ACCESS_TOKEN,
                        authRefreshResponse.accessToken
                    )
                    sharedPref.securelyPutString(
                        context, Constants.SHARED_PREF_TOKEN_TYPE,
                        authRefreshResponse.tokenType
                    )

                    refreshResponse = response.code()
                }

            })
        return refreshResponse

    }
}
