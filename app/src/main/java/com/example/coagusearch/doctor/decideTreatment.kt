package com.example.coagusearch.doctor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientPastDataAdapter
import com.example.coagusearch.doctor.doctorAdapters.suggestionAdapter
import com.example.coagusearch.network.PatientData.model.PatientDataRepository
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestDataRequest
import com.example.coagusearch.network.PatientData.request.GetPatientBloodTestRequest
import com.example.coagusearch.network.PatientData.response.SuggestionListResponse
import com.example.coagusearch.network.PatientData.response.SuggestionResponse
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.OrderForUserDataRequest
import kotlinx.android.synthetic.main.activity_add_medicine.*
import kotlinx.android.synthetic.main.activity_add_medicine.dosagePicked
import kotlinx.android.synthetic.main.activity_add_medicine.dosagePicker
import kotlinx.android.synthetic.main.activity_decide_treatment.*
import kotlinx.android.synthetic.main.activity_decision.*

import kotlinx.android.synthetic.main.activity_patient_past_data.*
import kotlinx.android.synthetic.main.doctoraddmedcard.*
import kotlinx.android.synthetic.main.doctoraddmedcard.view.*
import kotlinx.android.synthetic.main.doctordosagecard.*
import kotlinx.android.synthetic.main.doctordosagecard.view.*
import kotlinx.android.synthetic.main.patientbloodorder.*
import kotlinx.android.synthetic.main.patientbloodorder.view.*

import org.koin.android.ext.android.get

class decideTreatment : AppCompatActivity() {
    var list = mutableListOf("a", "a", "b", "b")
    var doslist = arrayOf("g", "mg", "Unit","ml/kg", "g/kg","U/kg")
    var bloodTestId: Long? = null
    var patientInfo: PatientDetailResponse? = null
    var meds = arrayOf("TXA", "FibrinogenConcentrate", "PCC")
    var patientid: Long? = null
    var dos:String?=null
    private var m_Text = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decide_treatment)
        if (intent.hasExtra("bloodTestId")) {
            val bundle: Bundle? = intent.extras
            bloodTestId = bundle!!.getLong("bloodTestId")
            if (intent.hasExtra("PatientDetailResponse")) {
                patientInfo =
                    intent.getSerializableExtra("PatientDetailResponse") as? PatientDetailResponse
            }

            if (intent.hasExtra("id")) {
                val bundle: Bundle? = intent.extras
                patientid = bundle!!.getLong("id")
            }
            getData()
            setListeners()
        } else {
            intentFailDialog(this)
        }
    }

    private fun getData() {
        val patientDataRepository: PatientDataRepository = get()
        patientDataRepository.getSuggestionOfBloodTest(
            GetPatientBloodTestDataRequest(bloodTestId!!),
            this
        )
    }

    fun setData(suggestionList: SuggestionListResponse) {
        suggestionsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        suggestionsRecyclerView.adapter =
            suggestionAdapter(suggestionList.userSuggestionList.toMutableList(),this)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun setSuggestion(suggestion:SuggestionResponse){

        if(suggestion.kind.equals("Medicine")){
            editText2.setText(suggestion.quantity.toString())
            dos=suggestion.unit
            dosagePicked.text = editText2.text.toString() + " " + dos
            addmedcard.medName.text=suggestion.product
        }
        else{

            if(patientInfo!!.patientResponse.isBloodDataMissing()){
                Toast.makeText(this,getString(R.string.nobloodtype),Toast.LENGTH_LONG).show()
            }
            /*
            else{
                if(suggestion.product.equals("FFP")){
                    bloodOrderCard.productTypeRadioGroup.check(R.id.FFP)
                }
                else{
                    bloodOrderCard.productTypeRadioGroup.check(R.id.PCC)
                }
                bloodOrderCard.editText.setText(suggestion.quantity.toInt().toString())
            }
             */

        }
    }



    private fun setListeners() {
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meds)
        multiAutoCompleteTextViewDoctor.setAdapter(arrayAdapter)
        dosagePicker.visibility = View.GONE
        dosagePicker.maxValue = doslist.size-1
        dosagePicker.minValue = 0
        dosagePicker.displayedValues = doslist
        dosagePicker.wrapSelectorWheel = false
        pickdosagecard.ocDosagePicker.setOnClickListener {
            if (dosagePicker.visibility == View.GONE) {
                dosagePicker.visibility = View.VISIBLE
                pickdosagecard.ocDosagePicker.rotation = 180F
            } else {
                dosagePicker.visibility = View.GONE
                pickdosagecard.ocDosagePicker.rotation = 0f
            }
        }
        multiAutoCompleteTextViewDoctor.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            addmedcard.medName.text = multiAutoCompleteTextViewDoctor.text
        }
        dosagePicker.setOnValueChangedListener { picker, oldVal, newVal ->
            dos= doslist[dosagePicker.value]
            dosagePicked.text = editText2.text.toString() + " " + doslist[dosagePicker.value]
        }
        editText2.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                closeSoftKeyboard(editText2)
                dosagePicked.text = editText2.text.toString() + " " + doslist[dosagePicker.value]
                return@OnKeyListener true
            }
            false
        })
        dosagePicker.setOnClickListener {
            if (dosagePicker.visibility == View.GONE) {
                dosagePicker.visibility = View.VISIBLE
                pickdosagecard.ocDosagePicker.rotation = 180F
            } else {
                dosagePicker.visibility = View.GONE
                pickdosagecard.ocDosagePicker.rotation = 0f
            }
        }


        if(patientInfo!!.patientResponse.isBloodDataMissing()){
            bloodOrderCard.isEnabled=false
            bloodOrderCard.alpha=0.5f
            bloodOrderCard.FFP.isEnabled=false
            bloodOrderCard.PCC.isEnabled=false
            bloodOrderCard.editText.isEnabled=false
            button.isEnabled=false
            button.alpha=0.5f
            Toast.makeText(this,getString(R.string.nobloodtype),Toast.LENGTH_LONG).show()
        }
        else {
            button.setOnClickListener {
                var bloodType: String? = patientInfo!!.patientResponse.bloodType
                var rhType: String? = patientInfo!!.patientResponse.rhType
                var productType: String? = null
                var unit: Double? = null
                var additionalNote: String? = null
                when (productTypeRadioGroup.checkedRadioButtonId) {
                    R.id.PCC -> productType = "FibrinogenConcentrate"
                    R.id.FFP -> productType = "FFP"
                }

                additionalNote = m_Text
                if (editText.text.toString().trim().length != 0) {
                    unit = editText.text.toString().toDouble()
                }
                if (bloodType != null && rhType != null && productType != null && unit != null) {
                    // val bloodbank: BloodOrderRepository = get( )
                    // bloodbank.bloodOrder(BloodOrderRequest(bloodType,rhType, productType, unit, additionalNote),this,)
                    m_Text = ""
                    editText.text = null
                    editText.clearFocus()
                    if (intent.hasExtra("id")) {
                        val bundle: Bundle? = intent.extras
                        patientid = bundle!!.getLong("id")
                        val bloodbank: BloodOrderRepository = get()
                        bloodbank.bloodOrderForUser(
                            OrderForUserDataRequest(
                                "",
                                "Blood",
                                unit,
                                productType,
                                bloodTestId!!
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


        button3.setOnClickListener {
            var unit: String? = null
            val kind = "Medicine"
            var quantity: Double? = null
            var product: String? = null
            val bloodTestId1 = bloodTestId

            product = addmedcard.medName.text.toString()
            if (editText2.text.toString().trim().length != 0) {
                quantity = editText2.text.toString().toDouble()
            }
            unit =dos
            val bloodbank: BloodOrderRepository = get()
            if (quantity != null && unit != null) {
                bloodbank.bloodOrderForUser(
                    OrderForUserDataRequest(
                        unit!!,
                        kind,
                        quantity,
                        product,
                        bloodTestId1!!
                    ), this
                )
            } else {
                Toast.makeText(this, getString(R.string.fillallparts), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
    private fun closeSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}
