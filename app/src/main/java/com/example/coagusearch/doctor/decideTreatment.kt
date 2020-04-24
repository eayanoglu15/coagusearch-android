package com.example.coagusearch.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientPastDataAdapter
import com.example.coagusearch.doctor.doctorAdapters.suggestionAdapter
import kotlinx.android.synthetic.main.activity_add_medicine.*
import kotlinx.android.synthetic.main.activity_decide_treatment.*
import kotlinx.android.synthetic.main.activity_patient_past_data.*

class decideTreatment : AppCompatActivity() {
    var list= mutableListOf("a","a","b","b")
    var doslist= arrayOf("gr","ml","ml/kg","l","kg")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decide_treatment)
        suggestionsRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        suggestionsRecyclerView.adapter= suggestionAdapter(list)


        dosagePicker.maxValue = 2
        dosagePicker.minValue = 0
        dosagePicker.displayedValues = doslist
        dosagePicker.wrapSelectorWheel = false
    }
}
