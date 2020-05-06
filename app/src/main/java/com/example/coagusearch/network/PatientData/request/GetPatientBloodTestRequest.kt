package com.example.coagusearch.network.PatientData.request


data class GetPatientBloodTestRequest (
    val patientId: Long
)

data class GetPatientBloodTestDataRequest (
    val bloodTestDataId: Long
)