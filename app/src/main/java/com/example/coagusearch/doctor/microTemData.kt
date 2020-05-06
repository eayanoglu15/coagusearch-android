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
import com.example.coagusearch.network.PatientData.model.PatientDataRepository
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestRequest
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataCategoryResponse
import com.example.coagusearch.network.PatientData.response.UserBloodTestDataResponse
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import kotlinx.android.synthetic.main.activity_micro_tem_data.*
import kotlinx.android.synthetic.main.segmentedcontrolbuttons.*
import org.koin.android.ext.android.get


class microTemData : AppCompatActivity() {
    //val list= mutableListOf("a","b","c","d","e","f","g","r","t")

    var categoryList1: MutableList<UserBloodTestDataCategoryResponse>?=null
    var categoryList2: MutableList<UserBloodTestDataCategoryResponse>?=null
    var categoryList3: MutableList<UserBloodTestDataCategoryResponse>?=null
    var state = 1;
    var patientid:Long?=null
    var patientInfo: PatientDetailResponse? = null
    var bloodTestLast: UserBloodTestDataResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_micro_tem_data)
        if (intent.hasExtra("id")) {
            val bundle: Bundle? = intent.extras
            patientid = bundle!!.getLong("id")
            getData()
        } else {
            intentFailDialog(this)
        }
        if (intent.hasExtra("PatientDetailResponse")) {
            patientInfo =
                intent.getSerializableExtra("PatientDetailResponse") as? PatientDetailResponse
        } else {
            intentFailDialog(this)
        }




    }


    private fun getData(){
        val patientDataRepository: PatientDataRepository = get()
        patientDataRepository.getLastOfPatient(GetPatientBloodTestRequest(patientid!!),this)


    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


    fun setData(bloodTestLast: UserBloodTestDataResponse){
        this.bloodTestLast=bloodTestLast
        categoryList1=bloodTestLast.userBloodData.get(0).categoryList.toMutableList()
        categoryList2=bloodTestLast.userBloodData.get(1).categoryList.toMutableList()
        categoryList3=bloodTestLast.userBloodData.get(2).categoryList.toMutableList()
        dataResultRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dataResultRecyclerView.adapter = utemResultAdapter(categoryList1!!)
        button1.text=bloodTestLast.userBloodData.get(0).testName
        button2.text=bloodTestLast.userBloodData.get(1).testName
        button3.text=bloodTestLast.userBloodData.get(2).testName
        button1.setOnClickListener {
            (dataResultRecyclerView.adapter as utemResultAdapter).companies = categoryList1!!
            (dataResultRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            button1.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button1.setTextColor(resources.getColor(R.color.white))
            button2.setBackgroundResource(R.drawable.segmentedbuttonback)
            button2.setTextColor(resources.getColor(R.color.colorPrimary))
            button3.setBackgroundResource(R.drawable.segmentedbuttonback)
            button3.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        button2.setOnClickListener {
            (dataResultRecyclerView.adapter as utemResultAdapter).companies = categoryList2!!
            (dataResultRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            button1.setBackgroundResource(R.drawable.segmentedbuttonback)
            button1.setTextColor(resources.getColor(R.color.colorPrimary))
            button2.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button2.setTextColor(resources.getColor(R.color.white))
            button3.setBackgroundResource(R.drawable.segmentedbuttonback)
            button3.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        button3.setOnClickListener {
            (dataResultRecyclerView.adapter as utemResultAdapter).companies = categoryList3!!
            (dataResultRecyclerView.adapter as utemResultAdapter).notifyDataSetChanged()
            button1.setBackgroundResource(R.drawable.segmentedbuttonback)
            button1.setTextColor(resources.getColor(R.color.colorPrimary))
            button2.setBackgroundResource(R.drawable.segmentedbuttonback)
            button2.setTextColor(resources.getColor(R.color.colorPrimary))
            button3.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button3.setTextColor(resources.getColor(R.color.white))
        }


        testResultscard.setOnClickListener {
            val intent = Intent(this, PatientPastDataActivity::class.java)
            intent.putExtra("id", patientid)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        takaActionButton.setOnClickListener {
            val intent = Intent(this, decisionActivity::class.java)
            intent.putExtra("id", patientid)
            intent.putExtra("PatientDetailResponse",patientInfo)
            intent.putExtra("bloodTestId", bloodTestLast.bloodTestId)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }







    }



}


