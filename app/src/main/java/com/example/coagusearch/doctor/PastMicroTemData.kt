package com.example.coagusearch.doctor

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientBloodOrderAdapter
import com.example.coagusearch.doctor.doctorAdapters.statusAdapter
import com.example.coagusearch.doctor.doctorAdapters.utemResultAdapter
import com.example.coagusearch.network.PatientData.model.PatientDataRepository
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestDataRequest
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataCategoryResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataResponse
import kotlinx.android.synthetic.main.activity_past_micro_tem_data.*
import kotlinx.android.synthetic.main.pastdatasegmented.*
import org.koin.android.ext.android.get


class PastMicroTemData : AppCompatActivity() {



    var categoryList1: MutableList<UserBloodTestDataCategoryResponse>?=null
    var categoryList2: MutableList<UserBloodTestDataCategoryResponse>?=null
    var categoryList3: MutableList<UserBloodTestDataCategoryResponse>?=null
    var bloodTest: UserBloodTestDataResponse? = null
    var testId:Long?=null
    var list= mutableListOf("a","b")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_micro_tem_data)
        if (intent.hasExtra("testId")) {
            val bundle: Bundle? = intent.extras
            testId = bundle!!.getLong("testId")
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
        patientDataRepository.getPatientBloodDataById(GetPatientBloodTestDataRequest(testId!!),this)
    }

    fun setData(bloodTest: UserBloodTestDataResponse){
        this.bloodTest=bloodTest
        categoryList1=bloodTest.userBloodData.get(0).categoryList.toMutableList()
        categoryList2=bloodTest.userBloodData.get(1).categoryList.toMutableList()
        categoryList3=bloodTest.userBloodData.get(2).categoryList.toMutableList()
        past_uTemDataRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        past_uTemDataRecyclerView.adapter = utemResultAdapter(categoryList1!!)
        ordersRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ordersRecyclerView.adapter = statusAdapter(bloodTest.ordersOfData.toMutableList(),this)
        ordersRecyclerView.visibility = View.GONE
        println(bloodTest.ordersOfData.toString())

        button1.text=bloodTest.userBloodData.get(0).testName.capitalize()
        button2.text=bloodTest.userBloodData.get(1).testName.capitalize()
        button3.text=bloodTest.userBloodData.get(2).testName.capitalize()
        button1.setOnClickListener {
            past_uTemDataRecyclerView.visibility = View.VISIBLE
            ordersRecyclerView.visibility = View.GONE
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).companies = categoryList1!!
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            button1.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button1.setTextColor(resources.getColor(R.color.white))
            button2.setBackgroundResource(R.drawable.segmentedbuttonback)
            button2.setTextColor(resources.getColor(R.color.colorPrimary))
            button3.setBackgroundResource(R.drawable.segmentedbuttonback)
            button3.setTextColor(resources.getColor(R.color.colorPrimary))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            ordersbutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        button2.setOnClickListener {
            past_uTemDataRecyclerView.visibility = View.VISIBLE
            ordersRecyclerView.visibility = View.GONE
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).companies = categoryList2!!
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            button1.setBackgroundResource(R.drawable.segmentedbuttonback)
            button1.setTextColor(resources.getColor(R.color.colorPrimary))
            button2.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button2.setTextColor(resources.getColor(R.color.white))
            button3.setBackgroundResource(R.drawable.segmentedbuttonback)
            button3.setTextColor(resources.getColor(R.color.colorPrimary))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            ordersbutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        button3.setOnClickListener {
            past_uTemDataRecyclerView.visibility = View.VISIBLE
            ordersRecyclerView.visibility = View.GONE
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).companies = categoryList3!!
            (past_uTemDataRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            button1.setBackgroundResource(R.drawable.segmentedbuttonback)
            button1.setTextColor(resources.getColor(R.color.colorPrimary))
            button2.setBackgroundResource(R.drawable.segmentedbuttonback)
            button2.setTextColor(resources.getColor(R.color.colorPrimary))
            button3.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button3.setTextColor(resources.getColor(R.color.white))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonback)
            ordersbutton.setTextColor(resources.getColor(R.color.colorPrimary))
        }

        ordersbutton.setOnClickListener {
            past_uTemDataRecyclerView.visibility = View.GONE
            ordersRecyclerView.visibility = View.VISIBLE
            button1.setBackgroundResource(R.drawable.segmentedbuttonback)
            button1.setTextColor(resources.getColor(R.color.colorPrimary))
            button2.setBackgroundResource(R.drawable.segmentedbuttonback)
            button2.setTextColor(resources.getColor(R.color.colorPrimary))
            button3.setBackgroundResource(R.drawable.segmentedbuttonback)
            button3.setTextColor(resources.getColor(R.color.colorPrimary))
            ordersbutton.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            ordersbutton.setTextColor(resources.getColor(R.color.white))
        }

    }
}
