package com.example.coagusearch.network.Users.response

import com.example.coagusearch.network.RegularMedication.request.KeyType
import com.example.coagusearch.network.notifications.response.NotificationResponse
import java.io.Serializable


data class PatientMainScreenResponse(
    var patientMissingInfo: Boolean = false,
    var patientNextAppointment: SingleAppointmentResponse?,
    val patientNotifications: List<NotificationResponse>
) : Serializable

data class SingleAppointmentResponse(
    var id: KeyType,
    var doctorName: String?,
    var doctorSurname: String?,
    var day: Int,
    var month: Int,
    var year: Int,
    var hour: Int,
    var minute: Int
) : Serializable {
    fun DoctorName(): String {
        var name = doctorName + " " + doctorSurname
        return name
    }

    fun appointmentDate(): String {
        var date = day.toString() + "/" + month.toString() + "/" + year
        return date
    }

    fun timeSlot(): String {
        var slot = hour.toString() + ":" + minute.toString()
        if(minute==0) slot+="0"
        return slot
    }

}



