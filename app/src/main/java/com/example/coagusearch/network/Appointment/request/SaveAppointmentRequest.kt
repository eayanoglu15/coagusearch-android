package com.example.coagusearch.network.Appointment.request

data class SaveAppointmentRequest(
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int
)