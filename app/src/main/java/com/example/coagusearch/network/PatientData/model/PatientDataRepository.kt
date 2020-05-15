package com.example.coagusearch.network.PatientData.model

import android.content.Context
import com.example.coagusearch.R
import com.example.coagusearch.doctor.*
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestDataRequest
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestRequest
import com.example.coagusearch.network.PatientData.response.SuggestionListResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestHistoryResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestsResponse
import com.example.coagusearch.network.RegularMedication.request.SaveMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.response.UserMedicineResponse
import com.example.coagusearch.network.onFailureDialog
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.patient.AddMedicine
import com.example.coagusearch.patient.UserInfoSingleton
import com.example.coagusearch.ui.dialog.showProgressLoading
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDataRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {

    fun getPatientBloodTest(
        patientId: GetPatientBloodTestRequest,
        context: Context
    ): UserBloodTestsResponse? {
        var bloodTestHistory: UserBloodTestsResponse? = null
        showProgressLoading(true,context)
        retrofitClient.patientDataApi().getPatientBloodTestRequest(patientId)
            .enqueue(object : Callback<UserBloodTestsResponse> {
                override fun onFailure(call: Call<UserBloodTestsResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<UserBloodTestsResponse>,
                    response: Response<UserBloodTestsResponse>
                ) {
                    if(response.isSuccessful && response.body() is UserBloodTestsResponse) {
                        bloodTestHistory = response.body()
                        (context as PatientPastDataActivity).setData(bloodTestHistory!!)
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
        return bloodTestHistory
    }

    fun getLastOfPatient(
        patientId: GetPatientBloodTestRequest,
        context: Context
    ): UserBloodTestDataResponse? {
        var bloodTestLast: UserBloodTestDataResponse? = null
        showProgressLoading(true,context)
        retrofitClient.patientDataApi().getPatientBloodTestRequestLast(patientId)
            .enqueue(object : Callback<UserBloodTestDataResponse> {
                override fun onFailure(call: Call<UserBloodTestDataResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<UserBloodTestDataResponse>,
                    response: Response<UserBloodTestDataResponse>
                ) {

                    if (response.isSuccessful && response.body() is UserBloodTestDataResponse){
                        bloodTestLast = response.body()
                    (context as microTemData).setData(bloodTestLast!!)
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
        return bloodTestLast
    }


    fun getPatientBloodDataById(
        bloodTestId: GetPatientBloodTestDataRequest,
        context: Context
    ): UserBloodTestDataResponse? {
        var bloodTestHistory: UserBloodTestDataResponse? = null
         showProgressLoading(true,context)
        retrofitClient.patientDataApi().getPatientBloodDataById(bloodTestId)
            .enqueue(object : Callback<UserBloodTestDataResponse> {
                override fun onFailure(call: Call<UserBloodTestDataResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }
                override fun onResponse(
                    call: Call<UserBloodTestDataResponse>,
                    response: Response<UserBloodTestDataResponse>
                ) {
                    if(response.isSuccessful && response.body() is UserBloodTestDataResponse) {
                        bloodTestHistory = response.body()
                        if (context is PastMicroTemData) {
                            (context as PastMicroTemData).setData(bloodTestHistory!!)
                        } else if (context is treatmentStatus) {
                            (context as treatmentStatus).setData(bloodTestHistory!!)
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
        return bloodTestHistory
    }

    fun getSuggestionOfBloodTest(
        bloodTestId: GetPatientBloodTestDataRequest,
        context: Context
    ): SuggestionListResponse? {
        var suggestions: SuggestionListResponse? = null
        showProgressLoading(true,context)
        retrofitClient.patientDataApi().getSuggestionOfBloodTest(bloodTestId)
            .enqueue(object : Callback<SuggestionListResponse> {
                override fun onFailure(call: Call<SuggestionListResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }
                override fun onResponse(
                    call: Call<SuggestionListResponse>,
                    response: Response<SuggestionListResponse>
                ) {
                    if(response.isSuccessful && response.body() is SuggestionListResponse) {
                        suggestions = response.body()
                        (context as decideTreatment).setData(suggestions!!)
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
        return suggestions
    }
}