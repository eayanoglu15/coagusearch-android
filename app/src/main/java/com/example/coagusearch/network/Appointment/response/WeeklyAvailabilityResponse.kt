package com.example.coagusearch.network.Appointment.response

data class WeeklyAvailabilityResponse(
    var doctorName: String? = null,
    var doctorSurname: String? = null,
    val week: List<DailyAvailablityResponse>
) {

}

data class DailyAvailablityResponse(
    var day: Int,
    var month: Int,
    var year: Int,
    var hours: List<HoursAvailablityResponse>
) {
    override fun toString(): String {
        return day.toString() + "/" + month.toString() + "/" + year.toString()
    }

    fun getAvailableHours(): Array<String> {
        var listOfTicket = ArrayList<String>()
        val iterator = hours.listIterator()
        var next: HoursAvailablityResponse? = null
        while (iterator.hasNext()) {
            next = iterator.next()
            if (next.available) {
                listOfTicket.add(next.hour.toString() + ":" + next.minute.toString())
            }
        }
        val array = arrayOfNulls<String>(listOfTicket.size)
        listOfTicket.toArray(array)

        return array as Array<String>
    }

}

data class HoursAvailablityResponse(
    var hour: Int,
    var minute: Int,
    var available: Boolean = true
)
