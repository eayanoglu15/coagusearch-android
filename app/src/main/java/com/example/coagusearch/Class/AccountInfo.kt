package com.example.coagusearch.Class

import com.example.coagusearch.Class.BloodRh
import com.example.coagusearch.Class.BloodType

class AccountInfo {
    var userName:String?=null
    var userSurname:String?=null
    var userAge:Int?=null
    var userHeight:Int?=null
    var userWeight:Int?=null
    var bloodType: BloodType?=null
    var BloodRh: BloodRh?=null


    constructor(userName:String?, userSurname:String, userAge:Int, userWeight:Int, userHeight: Int, bloodType: BloodType, BloodRh: BloodRh){
        this.userName=userName
        this.userSurname=userSurname
        this.userAge=userAge
        this.userHeight=userHeight
        this.userWeight=userWeight
        this.bloodType=bloodType
        this.BloodRh=BloodRh
    }

    fun hasMissingInfo():Boolean{
        if(userName=="NI")return true
        if(userSurname=="NI") return true
        if(userAge==-1) return true
        if(userWeight==-1) return true
        if(userHeight==-1) return true
        if(bloodType== BloodType.Missing)return true
        if(BloodRh== com.example.coagusearch.Class.BloodRh.Missing)return true
        return false
    }

    fun ufu (){
        this.userName
    }

}