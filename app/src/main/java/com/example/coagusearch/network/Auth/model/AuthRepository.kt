package com.example.coagusearch.network.Auth.model

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.Constants
import com.example.coagusearch.MainActivity
import com.example.coagusearch.R
import com.example.coagusearch.network.Auth.request.LoginRequest
import com.example.coagusearch.network.Auth.request.RefreshRequest
import com.example.coagusearch.network.Auth.request.SignUpRequest
import com.example.coagusearch.network.Auth.response.LoginResponse
import com.example.coagusearch.network.Auth.response.RefreshResponse
import com.example.coagusearch.network.Auth.response.SignUpResponse
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.utils.PreferenceHelper
import com.example.coagusearch.utils.securelyGetString
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

    //TODO: Handle the Server error part which shows error on the screen
    fun signIn(identity_number: String, password: String,context: Context): LoginResponse? {
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
                        //(context as MainActivity).LoginButton.text =
                        //   response.body()?.tokenType.toString()
                        loginResponse = response.body()
                        println(
                            sharedPref.securelyGetString(
                                context,
                                Constants.SHARED_PREF_ACCESS_TOKEN
                            )
                        )
                        println(
                            sharedPref.securelyGetString(
                                context,
                                Constants.SHARED_PREF_REFRESH_TOKEN
                            )
                        )
                        (context as MainActivity).showProgressLoading(false)
                        (context as MainActivity).directedToMainScreen()
                    }else{
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(response.errorBody()?.string(), ApiResponse::class.java)?.message
                                ?: context.resources.getString(R.string.errorOccurred)

                        val builder = AlertDialog.Builder(context)
                        // Set the alert dialog title
                        builder.setTitle("App background color")
                        println(response.body())
                        // Display a message on alert dialog
                        builder.setMessage(errorResponse)
                        // Set a positive button and its click listener on alert dialog
                        builder.setPositiveButton("YES"){dialog, which ->
                            // Do something when user press the positive button
                            Toast.makeText(context,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
                        }
                        // Display a negative button on alert dialog
                        builder.setNegativeButton("No"){dialog,which ->
                            Toast.makeText(context,"You are not agree.",Toast.LENGTH_SHORT).show()
                        }
                        // Display a neutral button on alert dialog
                        builder.setNeutralButton("Cancel"){_,_ ->
                            Toast.makeText(context,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
                        }
                        // Finally, make the alert dialog using builder
                        val dialog: AlertDialog = builder.create()
                        // Display the alert dialog on app interface
                        dialog.show()
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
