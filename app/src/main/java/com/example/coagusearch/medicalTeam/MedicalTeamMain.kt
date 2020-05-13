package com.example.coagusearch.medicalTeam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.PatientBloodOrder
import com.example.coagusearch.medicalTeam.medTeamAdapters.MedTeamMainPageAdapter
import kotlinx.android.synthetic.main.activity_medical_team_main.*
import kotlinx.android.synthetic.main.reportpatientcard.*

class MedicalTeamMain : AppCompatActivity() {
    val list = mutableListOf("a", "b", "c")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_team_main)
        managePatients.setOnClickListener {
            val intent = Intent(this, MedTeamAllPatients::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
       // notifyDoctorText.setOnClickListener {
        //    Toast.makeText(this, "Doktora Bildirildi", Toast.LENGTH_LONG).show()
         //   patientNameText.text = null;
          //  patientNameText.clearFocus()
        //}
        patientmainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        patientmainRecyclerView.adapter = MedTeamMainPageAdapter(list)
    }

    override fun onBackPressed() {
    }
}
