package com.example.coagusearch.doctor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientPastDataAdapter
import com.example.coagusearch.network.PatientData.model.PatientDataRepository
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestRequest
import com.example.coagusearch.network.PatientData.response.UserBloodTestsResponse
import kotlinx.android.synthetic.main.activity_patient_past_data.*
import org.koin.android.ext.android.get

class PatientPastDataActivity : AppCompatActivity() {
    val list = mutableListOf("a", "b")
    var patientid:Long?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_past_data)
        if (intent.hasExtra("id")) {
            val bundle: Bundle? = intent.extras
            patientid = bundle!!.getLong("id")
            getData()
        } else {
            intentFailDialog(this)
        }




    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    private fun getData(){
        val patientDataRepository: PatientDataRepository = get()
        patientDataRepository.getPatientBloodTest(GetPatientBloodTestRequest(patientid!!),this)
    }

    fun setData(bloodTestHistory: UserBloodTestsResponse){
        pastDataAnalysisRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        pastDataAnalysisRecyclerView.adapter = PatientPastDataAdapter(bloodTestHistory.userTestList.toMutableList(), this)

    }

}