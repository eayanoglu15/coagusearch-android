package com.example.coagusearch.network.RegularMedication.model

import android.content.Context
import com.example.coagusearch.patient.AddMedicine
import com.example.coagusearch.patient.UserInfoSingleton
import com.example.coagusearch.network.RegularMedication.request.DeleteMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.request.SaveMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.response.AllDrugInfoResponse
import com.example.coagusearch.network.RegularMedication.response.UserMedicineResponse
import com.example.coagusearch.network.onFailureDialog
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.ui.dialog.showProgressLoading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegularMedicationRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {

    fun getAllDrugs(): AllDrugInfoResponse? {
        var allDrugInfoResponse: AllDrugInfoResponse? = null
        retrofitClient.regularMedicationApi().getAllDrugs()
            .enqueue(object : Callback<AllDrugInfoResponse> {
                override fun onFailure(call: Call<AllDrugInfoResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<AllDrugInfoResponse>,
                    response: Response<AllDrugInfoResponse>
                ) {

                }
            })
        return allDrugInfoResponse

    }

    fun getUsersDrug(context: Context): UserMedicineResponse? {
        var userMedicineResponse: UserMedicineResponse? = null
        retrofitClient.regularMedicationApi().getUsersDrug()
            .enqueue(object : Callback<UserMedicineResponse> {
                override fun onFailure(call: Call<UserMedicineResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<UserMedicineResponse>,
                    response: Response<UserMedicineResponse>
                ) {
                    userMedicineResponse = response.body()
                    UserInfoSingleton.instance.medInfo = userMedicineResponse
                    println("burdaaa" + userMedicineResponse!!.allDrugs.frequencies.toString())
                    showProgressLoading(false, context)
                }
            })
        return userMedicineResponse
    }

    fun saveMedicine(
        saveMedicineInfoRequest: SaveMedicineInfoRequest,
        context: Context
    ): UserMedicineResponse? {
        var userMedicineResponse: UserMedicineResponse? = null
        // showProgressLoading(true,context)
        retrofitClient.regularMedicationApi().saveMedicine(saveMedicineInfoRequest)
            .enqueue(object : Callback<UserMedicineResponse> {
                override fun onFailure(call: Call<UserMedicineResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<UserMedicineResponse>,
                    response: Response<UserMedicineResponse>
                ) {
                    userMedicineResponse = response.body()
                    UserInfoSingleton.instance.medInfo = userMedicineResponse
                    //showProgressLoading(false,context)
                    (context as AddMedicine).onBackPressed()
                }
            })
        return userMedicineResponse

    }

    fun deleteMedicine(
        deleteMedicineInfoRequest: DeleteMedicineInfoRequest,
        context: Context
    ): UserMedicineResponse? {
        var userMedicineResponse: UserMedicineResponse? = null
        showProgressLoading(true, context)
        retrofitClient.regularMedicationApi().deleteMedicine(deleteMedicineInfoRequest)
            .enqueue(object : Callback<UserMedicineResponse> {
                override fun onFailure(call: Call<UserMedicineResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<UserMedicineResponse>,
                    response: Response<UserMedicineResponse>
                ) {
                    userMedicineResponse = response.body()
                    UserInfoSingleton.instance.medInfo = userMedicineResponse
                    showProgressLoading(false, context)
                    (context as AddMedicine).onBackPressed()
                }
            })
        return userMedicineResponse

    }
}