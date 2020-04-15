package com.example.coagusearch.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientPastDataAdapter
import kotlinx.android.synthetic.main.activity_patient_past_data.*
import kotlinx.android.synthetic.main.fragment_doctor_blood_bank.*

class PatientPastDataActivity : AppCompatActivity() {
    val list= mutableListOf("a","b")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_past_data)
        pastDataAnalysisRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        pastDataAnalysisRecyclerView.adapter=PatientPastDataAdapter(list,this)
    }
}