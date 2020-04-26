package com.example.coagusearch.network.RegularMedication.request

data class DeleteMedicineInfoRequest(
    val medicineId: KeyType
)

typealias KeyType = Long