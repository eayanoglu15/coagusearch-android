package com.example.coagusearch.doctor

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.BloodBankOrderAdapter
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse

import kotlinx.android.synthetic.main.bloodordercard.*
import kotlinx.android.synthetic.main.fragment_doctor_blood_bank.*
import org.koin.android.ext.android.get


class doctorBloodBankFragment : Fragment() {
    val list = mutableListOf("a", "b", "c", "a", "c", "b")
    private var m_Text = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_blood_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       getData()
        setListeners()


    }


    private fun setListeners(){
        addNoteText.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context!!)
            builder.setTitle("Title")
            val input = EditText(this.context!!)
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
            var bloodType: String?=null
            var rhType: String?=null
            var productType: String?=null
            var unit: Int?=null
            var additionalNote: String?=null
            when (productRadioGroup.checkedRadioButtonId) {
                R.id.pcc -> productType = "PCC"
                R.id.ffp -> productType = "FFP"
            }

            when (bloodTypeRadioGroup.checkedRadioButtonId) {
                R.id.A -> bloodType = "A"
                R.id.B -> bloodType = "B"
                R.id.O -> bloodType = "O"
                R.id.AB -> bloodType = "AB"
            }
            when (rhRadioGroup.checkedRadioButtonId) {
                R.id.Positive -> rhType = "Positive"
                R.id.Negative -> rhType = "Negative"
            }
            additionalNote=m_Text
            if(editText.text.toString().trim().length!=0) {
                unit = editText.text.toString().toInt()
            }
            if(bloodType!=null&&rhType!=null&&productType!=null&&unit!=null){
                val bloodbank: BloodOrderRepository = get()
                bloodbank.bloodOrder(BloodOrderRequest(bloodType,rhType,null, productType, unit, additionalNote),this.context!!,this)
                m_Text = ""
                editText.text = null
                editText.clearFocus()
            }
            else{
                Toast.makeText(this.context!!,"Lütfen Tüm Bilgileri DOldurunuz",Toast.LENGTH_LONG).show()
            }

        }
    }
    fun refresh(){
        getData()
    }
    private fun getData(){
        val bloodbank: BloodOrderRepository = get()
        bloodbank.previousOrders(this.context!!,this)
    }
    fun setData( orderResult: List<DoctorBloodOrderResponse>){
        ordersRecyclerView.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        ordersRecyclerView.adapter = BloodBankOrderAdapter(orderResult.toMutableList())

    }

}
