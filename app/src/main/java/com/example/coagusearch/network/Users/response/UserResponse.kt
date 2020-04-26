package com.example.coagusearch.network.Users.response

import java.io.Serializable

data class UserResponse(
    val identityNumber: String,
    val type: String,
    val userId: Long,
    var name: String?,
    var surname: String?,
    var birthDay: Int?,
    var birthMonth: Int?,
    var birthYear: Int?,
    var height: Double?,
    var weight: Double?,
    var bloodType: String?,
    var rhType: String?,
    var gender: String?
):Serializable{
    fun getFullName():String{
        return name+" "+surname
    }
    fun getFullBloodType():String{
        var blood:String=""
        if(bloodType==null){
            return "-"
        }
        else if(bloodType.equals("O")){
            blood+="0"
        }
        else blood+=bloodType

        if(rhType!=null){
            if(rhType!!.toLowerCase().equals("positive")||rhType!!.toLowerCase().equals("pozitif"))
            blood+=" Rh "+"+"
            else blood+=" Rh "+"-"
        }
        return blood
    }
}