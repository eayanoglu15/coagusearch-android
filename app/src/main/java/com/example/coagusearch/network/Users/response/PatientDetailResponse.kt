package com.example.coagusearch.network.Users.response

import com.example.coagusearch.network.Appointment.response.UserAppointmentResponse
import com.example.coagusearch.network.PatientData.response.PatientDataResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestHistoryResponse
import com.example.coagusearch.network.RegularMedication.response.MedicineInfoResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.UserBloodOrderResponse

import java.io.Serializable

data class PatientDetailResponse(
    val patientResponse: UserResponse,
    val userAppointmentResponse: UserAppointmentResponse,
    val lastDataAnalysisTime: UserBloodTestHistoryResponse? = null,
    val userDataResponse: PatientDataResponse,
    val patientDrugs: List<MedicineInfoResponse>,
    val previousBloodOrders: List<UserBloodOrderResponse>
) : Serializable
