package com.example.coagusearch.network.Users.response

import kotlin.random.Random

data class DoctorMainScreenResponse(
    val emergencyPatients: List<EmergencyPatientDetail>,
    val todayAppointments: List<TodayPatientDetail>
)

data class TodayPatientDetail(
    val patientId: Long,
    val userName: String,
    val userSurname: String,
    val appointmentHour: PatientAppointmentTimeResponse
)
data class PatientAppointmentTimeResponse(
    val hour: Int,
    val minute: Int
) {
    fun getTimeAsString(): String {
        var s: String = hour.toString() + ":"
        if (minute == 0) {
            s=s+ minute.toString()
            s += "0"
        }
        else if(minute<10){
            s+="0"
            s=s+ minute.toString()
        }
        else{
            s=s+ minute.toString()
        }

        return s
    }
}
data class EmergencyPatientDetail(
    val patientId: Long,
    val userName: String,
    val userSurname: String,
    val arrivalHour: PatientAppointmentTimeResponse,
    val isUserAtAmbulance:Boolean,
    val isDataReady: Boolean

)
