package com.example.coagusearch.patient.PatientAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.coagusearch.R
import com.example.coagusearch.network.RegularMedication.request.MedicineInfoType
import com.example.coagusearch.network.RegularMedication.response.MedicineInfoResponse
import com.example.coagusearch.patient.AddMedicine
import kotlinx.android.synthetic.main.medicinecard.view.*


class MedicineAdapterProfileFragment : BaseAdapter {
    var listOfTicket = ArrayList<MedicineInfoResponse>()
    var context: Context? = null

    constructor(context: Context, listOfTicket: ArrayList<MedicineInfoResponse>) : super() {
        this.listOfTicket = listOfTicket
        this.context = context
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val ticket = listOfTicket[p0]
        var inflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var myView = inflater.inflate(R.layout.medicinecard, null)
        if(ticket.mode==MedicineInfoType.KEY){
            myView.medName.text=ticket.key!!.capitalize()
        }
        else{
            myView.medName.text=ticket.custom!!.capitalize()
        }
        myView.medFreq.text = ticket.frequency!!.title.capitalize()
        myView.medDos.text = ticket.dosage.toString().capitalize()
        myView.editButtonMed.setOnClickListener {
            val intent = Intent(context, AddMedicine::class.java)
            intent.putExtra("type", "edit")
            intent.putExtra("Medicine",ticket)
            context!!.startActivity(intent)

        }
        return myView
    }

    override fun getItem(p0: Int): Any {
        return listOfTicket[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listOfTicket.size
    }
}