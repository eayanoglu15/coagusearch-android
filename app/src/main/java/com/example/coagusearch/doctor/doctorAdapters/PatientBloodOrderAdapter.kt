package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.UserBloodOrderResponse

class PatientBloodOrderAdapter(val companies: MutableList<DoctorBloodOrderResponse>) :
    RecyclerView.Adapter<PatientBloodOrderAdapter.PatientBloodOrderViewHolder>() {
    private val TYPE_EMER = 1
    private val TYPE_DATA = 2
    private val TYPE_NOTIF = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientBloodOrderViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientbloodorderconfirmed, parent, false)
        } else if (viewType == 2) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientbloodorderdenied, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientbloodorderpending, parent, false)
        }

        return PatientBloodOrderViewHolder(v)

    }

    override fun getItemCount(): Int {
        return companies.size
    }


    override fun onBindViewHolder(holder: PatientBloodOrderViewHolder, position: Int) {
        val company = companies[position]
        holder.name.text=company.productType
        holder.unit.text=company.quantity.toString()+" Unit"
    }

    override fun getItemViewType(position: Int): Int {
       /*
        return if (companies.get(position).equals("a")) {
            TYPE_EMER
        } else if (companies.get(position).equals("b")) {
            TYPE_DATA
        } else {
            TYPE_NOTIF
        }

        */
        return TYPE_EMER
    }
    class PatientBloodOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name = itemView.findViewById<TextView>(R.id.name)
        var unit = itemView.findViewById<TextView>(R.id.unit)
    }
}




class PatientBloodOrderAdapter2(val companies: MutableList<UserBloodOrderResponse>) :
    RecyclerView.Adapter<PatientBloodOrderAdapter2.PatientBloodOrderViewHolder>() {
    private val TYPE_EMER = 1
    private val TYPE_DATA = 2
    private val TYPE_NOTIF = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientBloodOrderViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientbloodorderconfirmed, parent, false)
        } else if (viewType == 2) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientbloodorderdenied, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientbloodorderpending, parent, false)
        }

        return PatientBloodOrderViewHolder(v)

    }

    override fun getItemCount(): Int {
        return companies.size
    }


    override fun onBindViewHolder(holder: PatientBloodOrderViewHolder, position: Int) {
        val company = companies[position]
        holder.name.text=company.productType
        holder.unit.text=company.quantity.toString()+" Unit"
    }

    override fun getItemViewType(position: Int): Int {
        /*
         return if (companies.get(position).equals("a")) {
             TYPE_EMER
         } else if (companies.get(position).equals("b")) {
             TYPE_DATA
         } else {
             TYPE_NOTIF
         }
         */
        return TYPE_EMER
    }
    class PatientBloodOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name = itemView.findViewById<TextView>(R.id.name)
        var unit = itemView.findViewById<TextView>(R.id.unit)
    }
}








