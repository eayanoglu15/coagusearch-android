package com.example.coagusearch.doctor

import android.app.Activity
import android.app.Notification
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientOldAppointmentAdapter
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.Users.response.SingleAppointmentResponse
import kotlinx.android.synthetic.main.activity_patient_appointments.*
import kotlinx.android.synthetic.main.fragment_doctor_home.*
import kotlinx.android.synthetic.main.mypatientnextappointment.view.*

class PatientAppointments : AppCompatActivity() {
    val list= mutableListOf("a","b","c")
    var patientInfo: PatientDetailResponse?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_appointments)

        if(intent.hasExtra("PatientDetailResponse")) {
            patientInfo = intent.getSerializableExtra("PatientDetailResponse") as? PatientDetailResponse
            setData()
            println("sdasdads"+patientInfo.toString())
        }
        else{
            intentFailDialog(this)
        }
    }
    private fun setData(){
        if(patientInfo!=null&&patientInfo!!.userAppointmentResponse!=null&&patientInfo!!.userAppointmentResponse.nextAppointment!=null) {
            nextAppointment.dateOfNext.text =
                patientInfo!!.userAppointmentResponse.nextAppointment!!.appointmentDate()
            nextAppointment.timeSlotNext.text =
                patientInfo!!.userAppointmentResponse.nextAppointment!!.timeSlot()
        }
        else{
            nextAppointment.visibility=View.GONE
        }
        if(patientInfo!=null&&patientInfo!!.userAppointmentResponse.oldAppointment!=null)
        oldAppointmentRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        oldAppointmentRecyclerView.adapter=PatientOldAppointmentAdapter(patientInfo!!.userAppointmentResponse.oldAppointment.toMutableList())
    }
}

fun intentFailDialog(context: Context){
    val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
    builder.setTitle("Error")
    builder.setMessage("Hata ile Karşılaşıldı")
    builder.setPositiveButton("OK"){dialog, which ->
        ( context as Activity).onBackPressed()
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}
