package com.example.coagusearch.doctor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.utemResultAdapter
import kotlinx.android.synthetic.main.activity_micro_tem_data.*


class microTemData : AppCompatActivity() {
    //val list= mutableListOf("a","b","c","d","e","f","g","r","t")
    val list= mutableListOf("a","b","c")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_micro_tem_data)
        dataResultRecyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        dataResultRecyclerView.adapter= utemResultAdapter(list)
        testResultscard.setOnClickListener{
            val intent = Intent(this, PatientPastDataActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}


