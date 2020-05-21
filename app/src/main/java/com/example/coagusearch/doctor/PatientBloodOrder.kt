package com.example.coagusearch.doctor

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientBloodOrderAdapter2
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.PatientDetailRequest
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import kotlinx.android.synthetic.main.activity_patient_blood_order.*
import kotlinx.android.synthetic.main.patientbloodorder.*
import kotlinx.android.synthetic.main.patientbloodorder.view.*
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
        if(patientInfo!!.patientResponse.isBloodDataMissing()){
            makeorderbutton.isEnabled=false
            makeorderbutton.alpha=0.5f
            bloodOrderCard.isEnabled=false
            bloodOrderCard.alpha=0.5f
            bloodOrderCard.FFP.isEnabled=false
            bloodOrderCard.PCC.isEnabled=false
            bloodOrderCard.editText.isEnabled=false
            Toast.makeText(this,getString(R.string.nobloodtype),Toast.LENGTH_LONG).show()
        }
        else {
            addNoteText.setOnClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Title")
                val input = EditText(this)
                input.isSingleLine = false
                input.setText(m_Text)
                input.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                builder.setTitle("Add Your Note")
                builder.setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, which ->
                        m_Text = input.text.toString()
                    })
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                builder.setView(input)
                builder.show()
            }

            makeorderbutton.setOnClickListener {
                var bloodType: String? = patientInfo!!.patientResponse.bloodType
                var rhType: String? = patientInfo!!.patientResponse.rhType
                var productType: String? = null
                var unit: Int? = null
                var additionalNote: String? = null
                when (productTypeRadioGroup.checkedRadioButtonId) {
                    R.id.PCC -> productType = "PlateletConcentrate"
                    R.id.FFP -> productType = "FFP"
                }
                productTypeRadioGroup.clearCheck()

                additionalNote = m_Text
                if (editText.text.toString().trim().length != 0) {
                    unit = editText.text.toString().toInt()
                }
                if (bloodType != null && rhType != null && productType != null && unit != null) {
                    // val bloodbank: BloodOrderRepository = get()
                    // bloodbank.bloodOrder(BloodOrderRequest(bloodType,rhType, productType, unit, additionalNote),this,)
                    m_Text = ""
                    editText.text = null
                    editText.clearFocus()
                    if (intent.hasExtra("id")) {
                        val bundle: Bundle? = intent.extras
                        patientid = bundle!!.getLong("id")
                        println(patientid.toString()+"ID printed")
                        val bloodbank: BloodOrderRepository = get()
                        bloodbank.bloodOrderForPatient(
                            BloodOrderRequest(
                                bloodType,
                                rhType,
                                patientid,
                                productType,
                                unit,
                                additionalNote
                            ), this
                        )

                    } else {
                        intentFailDialog(this)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.fillallparts), Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }
    fun refresh(){
        val userRepository: UsersRepository = get()
        userRepository.getPatientDetail(this, PatientDetailRequest(patientid!!))
    }

  fun setData(patientInfo:PatientDetailResponse){
      println(patientInfo.toString())
        patientOrderRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        patientOrderRecyclerView.adapter = PatientBloodOrderAdapter2(patientInfo.previousBloodOrders.filter {e-> e.kind.equals("Blood") }.toMutableList(),this)
    }
}