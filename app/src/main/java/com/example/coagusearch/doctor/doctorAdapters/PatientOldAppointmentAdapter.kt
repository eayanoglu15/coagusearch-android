package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R


class PatientOldAppointmentAdapter(val companies : MutableList<String>) :
        RecyclerView.Adapter<PatientOldAppointmentAdapter.PatientOldAppointmentViewHolde>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientOldAppointmentViewHolde {

        var v = LayoutInflater.from(parent.context).inflate(R.layout.mypatientoldappointments,parent,false)
        return PatientOldAppointmentViewHolde(v)

    }
    override fun getItemCount(): Int {
        return companies.size
    }

    fun add(item:String, position:Int) {
        companies.add(position, item)
        notifyItemInserted(position)
    }
    fun remove(item:String) {
        val position = companies.indexOf(item)
        companies.removeAt(position)
        notifyItemRemoved(position)
    }
    override fun onBindViewHolder(holder: PatientOldAppointmentViewHolde, position: Int) {
        val company = companies[position]
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context,"${position} is clicked",
            Toast.LENGTH_SHORT).show() }
        holder.itemView.setOnLongClickListener {
            remove(company)
            return@setOnLongClickListener true
        }
    }
    class PatientOldAppointmentViewHolde(itemView: View): RecyclerView.ViewHolder(itemView)
}

