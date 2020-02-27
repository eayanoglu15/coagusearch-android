package com.example.coagusearch.Class

class Appointment{
    var date:Date?=null
    var doctorName:String?=null
    var time:Time?=null
    var isNext:Boolean?=null
    var isVisible:Boolean?=null

    constructor(date: Date,doctorName:String,time:Time,isNext:Boolean,isVisible:Boolean){
        this.date=date
        this.doctorName=doctorName
        this.time=time
        this.isNext=isNext
        this.isVisible=isVisible
    }
}