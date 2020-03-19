package com.example.coagusearch.network.Users.model

import android.content.Context
import com.example.coagusearch.network.Users.request.UserBodyInfoSaveRequest
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class UsersRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {
    //TODO: Handle the Server error part which shows error on the screen
    fun getUserInfo(): UserResponse? {
        var userResponse: UserResponse? = null
        retrofitClient.usersApi().getUserInfo()
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    println( "Failure")

                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ){
                    println( "Entered get User Info")
                    println(response.body().toString())
                }

            })
        return userResponse

    }

    fun getMyPatients(): List<UserResponse>? {
        var myPatients: List<UserResponse>? = null
        retrofitClient.usersApi().getMyPatient()
            .enqueue(object : Callback<List<UserResponse>> {
                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ){

                    myPatients = response.body()
                }

            })
        return myPatients

    }

    fun saveBodyInfo(saveBodyInfoSaveRequest: UserBodyInfoSaveRequest): ApiResponse? {
        var apiResponse: ApiResponse? = null
        retrofitClient.usersApi().saveBodyInfo(saveBodyInfoSaveRequest)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {


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