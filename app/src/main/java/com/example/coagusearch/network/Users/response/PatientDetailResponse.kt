package com.example.coagusearch.network.Users.response

import com.example.coagusearch.network.Appointment.response.UserAppointmentResponse
import com.example.coagusearch.network.PatientData.model.PatientDataResponse

data class PatientDetailResponse(
    val patientResponse: UserResponse,
    val userAppointmentResponse: UserAppointmentResponse,
    val userDataResponse: PatientDataResponse
)
