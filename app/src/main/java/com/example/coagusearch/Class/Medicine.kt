package com.example.coagusearch.Class

class Medicine {
    var name:String?=null
    var frequency: MedicineFrequency?=null
    var dosage:Int?=null
    constructor(name:String, frequency: MedicineFrequency, dosage: Int){
        this.name=name
        this.frequency=frequency
        this.dosage=dosage
    }

}