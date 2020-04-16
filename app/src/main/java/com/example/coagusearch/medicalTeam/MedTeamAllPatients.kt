package com.example.coagusearch.medicalTeam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientsFragmentPatientAdapter
import com.example.coagusearch.medicalTeam.medTeamAdapters.MedTeamMainPageAdapter
import com.example.coagusearch.medicalTeam.medTeamAdapters.MedTeamPatientsAdapter
import kotlinx.android.synthetic.main.activity_med_team_all_patients.*
import kotlinx.android.synthetic.main.activity_med_team_all_patients.patientRecyclerView
import kotlinx.android.synthetic.main.activity_med_team_all_patients.searchView
import kotlinx.android.synthetic.main.activity_medical_team_main.*
import kotlinx.android.synthetic.main.fragment_doctor_patients.*

class MedTeamAllPatients : AppCompatActivity() {
    val list= mutableListOf("Ökkeş Uğur Ulaş","Ege Melis Ayanoğlu","Muharrem Salel","Kazım Okan Akgül")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_team_all_patients)


        patientRecyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        patientRecyclerView.adapter= MedTeamPatientsAdapter(list,this)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                (patientRecyclerView.adapter as MedTeamPatientsAdapter).filter.filter(newText)
                return false
            }
        })
    }
}
