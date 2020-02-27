package com.example.coagusearch.Class


class User{
    var accountInfo:AccountInfo?=null
    var medicines= ArrayList<Medicine>()
    var appointments=ArrayList<Appointment>()
    private constructor(){

    }
    companion object{
        val instance:User by lazy{User()}
    }

}