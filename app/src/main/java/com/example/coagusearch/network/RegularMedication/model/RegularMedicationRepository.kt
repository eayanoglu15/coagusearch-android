package com.example.coagusearch.network.RegularMedication.model

import android.content.Context
import com.example.coagusearch.network.RegularMedication.request.SaveMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.response.AllDrugInfoResponse
import com.example.coagusearch.network.RegularMedication.response.UserMedicineResponse
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegularMedicationRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {

    //TODO: Handle the Server error part which shows error on the screen
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

    fun getUsersDrug(): UserMedicineResponse? {
        var userMedicineResponse: UserMedicineResponse? = null
        retrofitClient.regularMedicationApi().getUsersDrug()
            .enqueue(object : Callback<UserMedicineResponse> {
                override fun onFailure(call: Call<UserMedicineResponse>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<UserMedicineResponse>,
                    response: Response<UserMedicineResponse>
                ) {

                    userMedicineResponse = response.body()
                }

            })
        return userMedicineResponse

    }

    fun saveMedicine(saveMedicineInfoRequest: SaveMedicineInfoRequest): UserMedicineResponse? {
        var userMedicineResponse: UserMedicineResponse? = null
        retrofitClient.regularMedicationApi().saveMedicine(saveMedicineInfoRequest)
            .enqueue(object : Callback<UserMedicineResponse> {
                override fun onFailure(call: Call<UserMedicineResponse>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<UserMedicineResponse>,
                    response: Response<UserMedicineResponse>
                ) {

                    userMedicineResponse = response.body()
                }

            })
        return userMedicineResponse

    }
}