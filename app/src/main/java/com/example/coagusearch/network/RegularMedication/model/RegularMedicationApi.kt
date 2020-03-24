package com.example.coagusearch.network.RegularMedication.model

import com.example.coagusearch.network.RegularMedication.request.DeleteMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.request.SaveMedicineInfoRequest
import com.example.coagusearch.network.RegularMedication.response.AllDrugInfoResponse
import com.example.coagusearch.network.RegularMedication.response.UserMedicineResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegularMedicationApi {

    @Headers("RequireAuth:1")
    @GET("/drug/all")
    fun getAllDrugs(): Call<AllDrugInfoResponse>


    @Headers("RequireAuth:1")
    @GET("/drug/getByUser")
    fun getUsersDrug(): Call<UserMedicineResponse>


    @Headers("RequireAuth:1")
    @POST("/drug/saveRegularMedicine")
    fun saveMedicine(@Body saveMedicineInfoRequest: SaveMedicineInfoRequest): Call<UserMedicineResponse>


    @Headers("RequireAuth:1")
    @POST("/drug/deleteRegularMedication")
    fun deleteMedicine(@Body deleteMedicineInfoRequest: DeleteMedicineInfoRequest): Call<UserMedicineResponse>

}