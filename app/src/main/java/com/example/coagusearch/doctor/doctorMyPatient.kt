package com.example.coagusearch.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.PatientDetailRequest
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import kotlinx.android.synthetic.main.activity_doctor_my_patient.*
import kotlinx.android.synthetic.main.patientinfocard.view.*
import org.koin.android.ext.android.get

class doctorMyPatient : AppCompatActivity() {
    var patientInfo:PatientDetailResponse?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_my_patient)
        val bundle: Bundle? = intent.extras
        var patientid = bundle!!.getLong("id")

        val userRepository: UsersRepository = get()
        userRepository.getPatientDetail(this, PatientDetailRequest(patientid))?.let { setData(it) }
    }

    fun setData(patientDetailResponse: PatientDetailResponse){
        patientName.text=patientDetailResponse.patientResponse.getFullName().capitalize()
        //setPatientInfoCard
        var birthYear:String=patientDetailResponse.patientResponse.birthYear.toString()
        patientinfocard.birthYear.text=if(birthYear.equals("null")) "-" else birthYear
        var height:String=patientDetailResponse.patientResponse.height.toString()
        patientinfocard.patientheight.text=if(height.equals("null")) "-" else height+ getString(R.string.heightvcm)
        patientinfocard.bloodType.text=patientDetailResponse.patientResponse.getFullBloodType()
        var weight:String=patientDetailResponse.patientResponse.weight.toString()
        patientinfocard.weighttext.text=if(weight.equals("null")) "-" else weight+ getString(R.string.weightkg)

        var nextAppointment=patientDetailResponse.userAppointmentResponse.nextAppointment
        if(nextAppointment==null){
            nextAppointmentcard.visibility= View.GONE
        }
        else{
            callForAppointmentard.visibility=View.GONE
        }

        bloodOrderCard.setOnClickListener{
            val intent = Intent(this, PatientBloodOrder::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        oldAppointment.setOnClickListener{
            val intent = Intent(this, PatientAppointments::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        testResultscard.setOnClickListener{
            val intent = Intent(this, microTemData::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }




    }
}
