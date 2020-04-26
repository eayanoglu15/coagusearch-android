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
import kotlinx.android.synthetic.main.segmentedcontrolbuttons.*


class microTemData : AppCompatActivity() {
    //val list= mutableListOf("a","b","c","d","e","f","g","r","t")

    val list = mutableListOf("a", "b", "c")
    val list1 = mutableListOf("c")
    val list2 = mutableListOf("b", "a")
    var state = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_micro_tem_data)
        dataResultRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dataResultRecyclerView.adapter = utemResultAdapter(list)
        testResultscard.setOnClickListener {
            val intent = Intent(this, PatientPastDataActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        takaActionButton.setOnClickListener {
            val intent = Intent(this, decisionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        fibtembutton.setOnClickListener {
            (dataResultRecyclerView.adapter as utemResultAdapter).companies = list
            (dataResultRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            fibtembutton.setTextColor(resources.getColor(R.color.white))
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            extembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            intembutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        extembutton.setOnClickListener {
            (dataResultRecyclerView.adapter as utemResultAdapter).companies = list1
            (dataResultRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            fibtembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            extembutton.setTextColor(resources.getColor(R.color.white))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            intembutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        intembutton.setOnClickListener {
            (dataResultRecyclerView.adapter as utemResultAdapter).companies = list2
            (dataResultRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            fibtembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            extembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            intembutton.setTextColor(resources.getColor(R.color.white))
        }

    }
}


