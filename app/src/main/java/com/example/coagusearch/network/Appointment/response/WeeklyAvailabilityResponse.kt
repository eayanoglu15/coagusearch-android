package com.example.coagusearch.network.Appointment.response

data class WeeklyAvailabilityResponse(

    val week:List<DailyAvailablityResponse>
)

data class DailyAvailablityResponse (
    var day: Int,
    var month: Int,
    var year: Int,
    var hours: List<HoursAvailablityResponse>
)

data class HoursAvailablityResponse (
    var hour: Int,
    var minute: Int,
    var available: Boolean = true
)
