package com.example.coagusearch.network.Appointment.response

import com.example.coagusearch.network.Users.response.SingleAppointmentResponse
import java.io.Serializable

data class UserAppointmentResponse(
    var nextAppointment: SingleAppointmentResponse? = null,
    var oldAppointment: List<SingleAppointmentResponse>
):Serializable
