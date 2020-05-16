package com.example.coagusearch.network.bloodOrderAndRecommendation.model

import android.content.Context
import com.example.coagusearch.R
import com.example.coagusearch.doctor.PatientBloodOrder
import com.example.coagusearch.doctor.PatientPastDataActivity
import com.example.coagusearch.doctor.doctorBloodBankFragment
import com.example.coagusearch.medicalTeam.MedicalPrepareFragment
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestRequest
import com.example.coagusearch.network.PatientData.response.UserBloodTestsResponse
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderIDRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.OrderForUserDataRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.MedicalBloodOrderResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.PreviousOrderResponse
import com.example.coagusearch.network.onFailureDialog
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.ui.dialog.showProgressLoading
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BloodOrderRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {
    fun bloodOrder(
        order: BloodOrderRequest,
        context: Context,
        fragment:doctorBloodBankFragment
    ): ApiResponse? {
        var orderResult: ApiResponse? = null
        showProgressLoading(true,context)
        retrofitClient.bloodOrderApi().bloodOrderRequest(order)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    showProgressLoading(false, context)
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful && response.body() is ApiResponse){
                        orderResult = response.body()
                             if (fragment.isAdded) {
                                 fragment.refresh()
                            }
                        showProgressLoading(false, context)
                    }
                    else {
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
        return orderResult
    }

    fun bloodOrderForPatient(
        order: BloodOrderRequest,
        context: Context
    ): ApiResponse? {
        var orderResult: ApiResponse? = null
        showProgressLoading(true,context)
        retrofitClient.bloodOrderApi().bloodOrderRequest(order)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    showProgressLoading(false, context)
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if(response.isSuccessful && response.body() is ApiResponse) {
                        orderResult = response.body()
                        if (context is PatientBloodOrder) {
                            (context as PatientBloodOrder).refresh()
                        }
                        showProgressLoading(false, context)
                    }
                    else {
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
        return orderResult
    }

    fun bloodOrderForUser(
        order: OrderForUserDataRequest,
        context: Context
    ): ApiResponse? {
        var orderResult: ApiResponse? = null
        showProgressLoading(true,context)
        retrofitClient.bloodOrderApi().bloodOrderForUserData(order)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if(response.isSuccessful && response.body() is ApiResponse) {
                        orderResult = response.body()
                        showProgressLoading(false, context)
                    }
                    else {
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
        return orderResult
    }

    fun previousOrders(
        context: Context,
        fragment: doctorBloodBankFragment
    ): List<DoctorBloodOrderResponse>? {
        var orderResult: List<DoctorBloodOrderResponse>? = null
         showProgressLoading(true,context)
        retrofitClient.bloodOrderApi().previousOrders()
            .enqueue(object : Callback<List<DoctorBloodOrderResponse>> {
                override fun onFailure(call: Call<List<DoctorBloodOrderResponse>>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false,context)
                }
                override fun onResponse(
                    call: Call<List<DoctorBloodOrderResponse>>,
                    response: Response<List<DoctorBloodOrderResponse>>
                ) {
                    if (response.isSuccessful && response.body() is List<DoctorBloodOrderResponse>) {
                        orderResult = response.body()
                        println(orderResult.toString())
                        println(response.body().toString())
                        if(fragment.isAdded) {
                            fragment.setData(orderResult!!)
                        }
                        showProgressLoading(false,context)
                    }
                    else {
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
        return orderResult
    }

    fun getOrdersForMedical(
        context: Context,
        fragment: MedicalPrepareFragment
    ): MedicalBloodOrderResponse? {
        var orderResult: MedicalBloodOrderResponse? = null
         showProgressLoading(true,context)
        retrofitClient.bloodOrderApi().getOrdersForMedical()
            .enqueue(object : Callback<MedicalBloodOrderResponse> {
                override fun onFailure(call: Call<MedicalBloodOrderResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }
                override fun onResponse(
                    call: Call<MedicalBloodOrderResponse>,
                    response: Response<MedicalBloodOrderResponse>
                ) {
                    if (response.isSuccessful && response.body() is MedicalBloodOrderResponse) {
                        orderResult = response.body()
                        fragment.setData(orderResult!!)
                        showProgressLoading(false, context)
                    }
                    else {
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
        return orderResult
    }


    fun setOrderReady(
        order: BloodOrderIDRequest,
        context: Context,
        fragment: MedicalPrepareFragment
    ): MedicalBloodOrderResponse? {
        var orderResult: MedicalBloodOrderResponse? = null
        showProgressLoading(true,context)
        retrofitClient.bloodOrderApi().setOrderReady(order)
            .enqueue(object : Callback<MedicalBloodOrderResponse> {
                override fun onFailure(call: Call<MedicalBloodOrderResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }
                override fun onResponse(
                    call: Call<MedicalBloodOrderResponse>,
                    response: Response<MedicalBloodOrderResponse>
                ) {
                    if(response.isSuccessful && response.body() is MedicalBloodOrderResponse) {
                        orderResult = response.body()
                        fragment.getData()
                        showProgressLoading(false, context)
                    }
                    else {
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
        return orderResult
    }



}