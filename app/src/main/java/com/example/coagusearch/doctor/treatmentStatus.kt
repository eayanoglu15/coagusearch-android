package com.example.coagusearch.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientPastDataAdapter
import com.example.coagusearch.doctor.doctorAdapters.statusAdapter
import kotlinx.android.synthetic.main.activity_decide_treatment.*
import kotlinx.android.synthetic.main.activity_treatment_status.*

class treatmentStatus : AppCompatActivity() {
    var list= mutableListOf("a","a","b","c")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment_status)

        treatmentStatusRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        treatmentStatusRecyclerView.adapter= statusAdapter(list)
    }
}
