package com.example.coagusearch.doctor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.statusAdapter
import com.example.coagusearch.network.PatientData.model.PatientDataRepository
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestDataRequest
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataResponse
import kotlinx.android.synthetic.main.activity_treatment_status.*
import org.koin.android.ext.android.get

class treatmentStatus : AppCompatActivity() {
    var list = mutableListOf("a", "a", "b", "c")
    var bloodTestId:Long?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment_status)
        if (intent.hasExtra("bloodTestId")) {
            val bundle: Bundle? = intent.extras
            bloodTestId = bundle!!.getLong("bloodTestId")
            getData()
        }
        else{
            intentFailDialog(this)
        }
        val patientDataRepository: PatientDataRepository = get()
        patientDataRepository.getPatientBloodDataById(GetPatientBloodTestDataRequest(bloodTestId!!),this)

    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    private fun getData(){
        val patientDataRepository: PatientDataRepository = get()
        patientDataRepository.getPatientBloodDataById(GetPatientBloodTestDataRequest(bloodTestId!!),this)
    }
     fun setData(r: UserBloodTestDataResponse){
         println("burdayımmmm"+r.ordersOfData.toString())
         treatmentStatusRecyclerView.layoutManager =
             LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
         treatmentStatusRecyclerView.adapter = statusAdapter(r.ordersOfData.toMutableList(),this)

     }
}
