package com.example.coagusearch.network.RegularMedication.request


data class SaveMedicineInfoRequest(
    val id: Long?,
    val mode: MedicineInfoType,
    val key: String?,
    val uuid: String?,
    val customText: String?,
    val frequency: String?,
    var dosage: Double?
)

enum class MedicineInfoType {
    KEY,
    CUSTOM
}