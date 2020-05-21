package com.example.coagusearch.network.Auth.model

import android.content.Context
import android.widget.Toast
import com.example.coagusearch.Constants
import com.example.coagusearch.MainActivity
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.MedTeamAddPatient
import com.example.coagusearch.network.Auth.request.LoginRequest
import com.example.coagusearch.network.Auth.request.RefreshRequest
import com.example.coagusearch.network.Auth.request.SignUpRequest
import com.example.coagusearch.network.Auth.request.UserSaveRequest
import com.example.coagusearch.network.Auth.response.LoginResponse
import com.example.coagusearch.network.Auth.response.RefreshResponse
import com.example.coagusearch.network.Auth.response.SignUpResponse
import com.example.coagusearch.network.Auth.response.UserSaveResponse
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.onFailureDialog
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.ui.dialog.showProgressLoading
import com.example.coagusearch.utils.PreferenceHelper
import com.example.coagusearch.utils.securelyPutString
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(
    private val context: Context,
    private val authInterceptor: AuthInterceptor,
    private val retrofitClient: RetrofitClient
) {
    init {
        authInterceptor.initAuthRepository(this)
    }

    fun signIn(identity_number: String, password: String, context: Context): LoginResponse? {
        var loginResponse: LoginResponse? = null
        showProgressLoading(true, context)
        retrofitClient.authApi().signIn(LoginRequest(identity_number, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful && response.body() is LoginResponse) {
                        val auth = response.body() as LoginResponse
                        val sharedPref = PreferenceHelper.defaultPrefs(context)
                        sharedPref.securelyPutString(
                            context, Constants.SHARED_PREF_ACCESS_TOKEN, auth.accessToken
                                ?: ""
                        )
                        sharedPref.securelyPutString(
                            context, Constants.SHARED_PREF_REFRESH_TOKEN, auth.refreshToken
                                ?: ""
                        )
                        sharedPref.securelyPutString(
                            context, Constants.SHARED_PREF_TOKEN_TYPE, auth.tokenType
                                ?: ""
                        )
                        loginResponse = response.body()
                        showProgressLoading(false, context)
                        (context as MainActivity).directedToMainScreen()
                    } else {
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(
                                response.errorBody()?.string(),
                                ApiResponse::class.java
                            )?.message
                                ?: context.resources.getString(R.string.errorOccurred)
                        showProgressLoading(false, context)
                        onFailureDialog(context, errorResponse)
                    }
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
        println("Refresh called")
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


    fun saveBodyInfoOfPatient(
        saveBodyInfoSaveRequest: UserSaveRequest,
        context: Context
    ): UserSaveResponse? {
        var r: UserSaveResponse? = null
        showProgressLoading(true, context)
        retrofitClient.authApi().savePatient(saveBodyInfoSaveRequest)
            .enqueue(object : Callback<UserSaveResponse> {
                override fun onFailure(call: Call<UserSaveResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<UserSaveResponse>,
                    response: Response<UserSaveResponse>
                ) {
                    if (response.isSuccessful && response.body() is UserSaveResponse) {
                        r = response.body()
                        showProgressLoading(false, context)
                        Toast.makeText(context,context.getString(R.string.savedInfoPatient),Toast.LENGTH_LONG)
                        (context as MedTeamAddPatient).showPass(r!!)
                    } else {
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(
                                response.errorBody()?.string(),
                                ApiResponse::class.java
                            )?.message
                                ?: context.resources.getString(R.string.errorOccurred)
                        showProgressLoading(false, context)
                        onFailureDialog(context, errorResponse)
                    }
                }
            })
        return r
    }


}
