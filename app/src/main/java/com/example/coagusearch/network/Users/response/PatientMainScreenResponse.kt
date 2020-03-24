package com.example.coagusearch.network.Users.response

import com.example.coagusearch.network.RegularMedication.request.KeyType


data class PatientMainScreenResponse (
    var patientMissingInfo : Boolean=false,
    var patientNextAppointment : SingleAppointmentResponse?
)

data class SingleAppointmentResponse(
    var id: KeyType,
    var doctorName: String?,
    var doctorSurname: String?,
    var day: Int,
    var month: Int,
    var year: Int,
    var hour: Int,
    var minute: Int
){
    fun DoctorName():String{
        var name=doctorName+" "+doctorSurname
        return name
    }
    fun appointmentDate():String{
        var date=day.toString()+"/"+month.toString()+"/"+year
        return date
    }
    fun timeSlot():String{
        var slot=hour.toString()+":"+minute.toString()
        return slot
    }

}



