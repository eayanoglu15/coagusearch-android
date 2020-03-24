package com.example.coagusearch.network.Appointment.response

import com.example.coagusearch.network.Users.response.SingleAppointmentResponse

data class UserAppointmentResponse(
    var nextAppointment: SingleAppointmentResponse? = null,
    var oldAppointment: List<SingleAppointmentResponse>
)
