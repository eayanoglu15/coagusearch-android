package com.example.coagusearch.doctor

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientBloodOrderAdapter
import com.example.coagusearch.doctor.doctorAdapters.PatientBloodOrderAdapter2
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.PatientDetailRequest
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import kotlinx.android.synthetic.main.activity_patient_blood_order.*
import kotlinx.android.synthetic.main.patientbloodorder.*
import kotlinx.android.synthetic.main.patientbloodorder.addNoteText
import kotlinx.android.synthetic.main.patientbloodorder.editText
import org.koin.android.ext.android.get

class PatientBloodOrder : AppCompatActivity() {
    val list = mutableListOf("a", "b", "c")
    private var m_Text = ""
    var patientInfo: PatientDetailResponse? = null
    var patientid:Long?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_blood_order)
        if (intent.hasExtra("PatientDetailResponse")) {
            patientInfo =
                intent.getSerializableExtra("PatientDetailResponse") as? PatientDetailResponse
            println("sdasdads" + patientInfo.toString())
            patientName.text=patientInfo!!.patientResponse.getFullName().capitalize()
            setData(patientInfo!!)


            setListeners()
        } else {
            intentFailDialog(this)
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun setListeners(){
        addNoteText.setOnClickListener {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Title")
        val input = EditText(this)
        input.isSingleLine = false
        input.setText(m_Text)
        builder.setTitle("Add Your Note")
        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which -> m_Text = input.text.toString() })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.setView(input)
        builder.show()
    }

    makeorderbutton.setOnClickListener {
        var bloodType: String?=patientInfo!!.patientResponse.bloodType
        var rhType: String?=patientInfo!!.patientResponse.rhType
        var productType: String?=null
        var unit: Int?=null
        var additionalNote: String?=null
        when (productTypeRadioGroup.checkedRadioButtonId) {
            R.id.PCC -> productType = "PCC"
            R.id.FFP -> productType = "FFP"
        }

        additionalNote=m_Text
        if(editText.text.toString().trim().length!=0) {
            unit = editText.text.toString().toInt()
        }
        if(bloodType!=null&&rhType!=null&&productType!=null&&unit!=null){
           // val bloodbank: BloodOrderRepository = get()
           // bloodbank.bloodOrder(BloodOrderRequest(bloodType,rhType, productType, unit, additionalNote),this,)
            m_Text = ""
            editText.text = null
            editText.clearFocus()
            if (intent.hasExtra("id")) {
                val bundle: Bundle? = intent.extras
                patientid = bundle!!.getLong("id")
                val bloodbank: BloodOrderRepository = get()
                bloodbank.bloodOrderForPatient(BloodOrderRequest(bloodType,rhType,patientid,productType, unit, additionalNote),this)

            }
            else{
                intentFailDialog(this)
            }
        }
        else{
            Toast.makeText(this,"Lütfen Tüm Bilgileri DOldurunuz",Toast.LENGTH_LONG).show()
        }

    }
    }
    fun refresh(){
        val userRepository: UsersRepository = get()
        userRepository.getPatientDetail(this, PatientDetailRequest(patientid!!))

    }

  fun setData(patientInfo:PatientDetailResponse){
        patientOrderRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        patientOrderRecyclerView.adapter = PatientBloodOrderAdapter2(patientInfo.previousBloodOrders.toMutableList(),this)
    }
}