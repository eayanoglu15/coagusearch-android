package com.example.coagusearch.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientBloodOrderAdapter
import com.example.coagusearch.doctor.doctorAdapters.utemResultAdapter
import kotlinx.android.synthetic.main.activity_micro_tem_data.*
import kotlinx.android.synthetic.main.activity_past_micro_tem_data.*
import kotlinx.android.synthetic.main.pastdatasegmented.*
import kotlinx.android.synthetic.main.segmentedcontrolbuttons.*
import kotlinx.android.synthetic.main.segmentedcontrolbuttons.extembutton
import kotlinx.android.synthetic.main.segmentedcontrolbuttons.fibtembutton
import kotlinx.android.synthetic.main.segmentedcontrolbuttons.intembutton

class PastMicroTemData : AppCompatActivity() {
    val list= mutableListOf("a","b","c")
    val list1= mutableListOf("c")
    val list2= mutableListOf("b","a")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_micro_tem_data)
        past_uTemDataRecyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        past_uTemDataRecyclerView.adapter= utemResultAdapter(list)
        ordersRecyclerView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        ordersRecyclerView.adapter= PatientBloodOrderAdapter(list)
        ordersRecyclerView.visibility=View.GONE
        fibtembutton.setOnClickListener{
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).companies=list
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            ordersRecyclerView.visibility=View.GONE
            past_uTemDataRecyclerView.visibility=View.VISIBLE
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            fibtembutton.setTextColor(resources.getColor(R.color.white))
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            extembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            intembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            ordersbutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        extembutton.setOnClickListener{
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).companies=list1
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            ordersRecyclerView.visibility=View.GONE
            past_uTemDataRecyclerView.visibility=View.VISIBLE
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            fibtembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            extembutton.setTextColor(resources.getColor(R.color.white))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            intembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            ordersbutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        intembutton.setOnClickListener{
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).companies=list2
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            fibtembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            extembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            ordersRecyclerView.visibility=View.GONE
            past_uTemDataRecyclerView.visibility=View.VISIBLE
            intembutton.setTextColor(resources.getColor(R.color.white))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            ordersbutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }

        ordersbutton.setOnClickListener {
            past_uTemDataRecyclerView.visibility=View.GONE
            ordersRecyclerView.visibility=View.VISIBLE
            extembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            extembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            intembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            intembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            fibtembutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            fibtembutton.setTextColor(resources.getColor(R.color.colorPrimary))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            ordersbutton.setTextColor(resources.getColor(R.color.white))
        }
    }
}
