package com.example.coagusearch.network.RegularMedication.model

import com.example.coagusearch.network.RegularMedication.request.SaveMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.response.AllDrugInfoResponse
import com.example.coagusearch.network.RegularMedication.response.UserMedicineResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegularMedicationApi {
    @GET("/drug/all")
    fun getAllDrugs(): Call<AllDrugInfoResponse>

    @GET("/drug/getByUser")
    fun getUsersDrug(): Call<UserMedicineResponse>

    @POST("/drug/saveRegularMedicine")
    fun saveMedicine(@Body saveMedicineInfoRequest: SaveMedicineInfoRequest): Call<UserMedicineResponse>
}