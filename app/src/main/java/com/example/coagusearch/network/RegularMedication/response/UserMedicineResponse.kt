package com.example.coagusearch.network.RegularMedication.response

import com.example.coagusearch.network.RegularMedication.request.MedicineInfoType
import java.io.Serializable


data class UserMedicineResponse(
    val allDrugs: AllDrugInfoResponse,
    val userDrugs: List<MedicineInfoResponse>
):Serializable

data class MedicineInfoResponse(
    val id: Long,
    val mode: MedicineInfoType,
    val custom: String?,
    val key: String?,
    //val content: String?,
    val frequency: DrugFrequencyResponse?,
    val dosage: Double?
):Serializable

data class DrugFrequencyResponse(
    val key: String,
    val title: String
):Serializable
