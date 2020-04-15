package com.example.coagusearch.patient

import com.example.coagusearch.network.RegularMedication.response.UserMedicineResponse
import com.example.coagusearch.network.Users.response.UserResponse

class UserInfoSingleton {
    var userInfo: UserResponse? =null
    var medInfo: UserMedicineResponse?=null
    private constructor(){
    }
    companion object{
        val instance: UserInfoSingleton by lazy { UserInfoSingleton() }
    }

    fun getMedicineNames(): Array<String?> {
        var medicineList=ArrayList<String>()
        var iterator= medInfo?.allDrugs?.drugs?.listIterator()
        while(iterator!!.hasNext()){
            medicineList.add(iterator.next().content)
        }
        val array = arrayOfNulls<String>(medicineList.size)
        medicineList.toArray(array)
        return array
    }

    fun getFrequencyNames():Array<String?>{
        var freqList=ArrayList<String>()
        var iterator= medInfo?.allDrugs?.frequencies?.listIterator()
        while(iterator!!.hasNext()){
            freqList.add(iterator.next().title)
        }
        val array = arrayOfNulls<String>(freqList.size)
        freqList.toArray(array)
        return array
    }

    fun getMedineHashMap():HashMap<String,String>{
        var medicineHaspMap=HashMap<String,String>()
        var iterator= medInfo?.allDrugs?.drugs?.listIterator()
        while(iterator!!.hasNext()){
            var next = iterator.next()
            medicineHaspMap.put(next.content,next.key)
        }
        return medicineHaspMap
    }
    fun getFrequencyHashMap():HashMap<String,String>{
        var frequencyHashMap=HashMap<String,String>()
        var iterator= medInfo?.allDrugs?.frequencies?.listIterator()
        while(iterator!!.hasNext()){
            var next = iterator.next()
            frequencyHashMap.put(next.title,next.key)
        }
        return frequencyHashMap
    }
}