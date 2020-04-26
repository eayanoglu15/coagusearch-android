package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.SingleAppointmentResponse


class PatientOldAppointmentAdapter(val oldAppointmentsList: MutableList<SingleAppointmentResponse>) :
    RecyclerView.Adapter<PatientOldAppointmentAdapter.PatientOldAppointmentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientOldAppointmentViewHolder {

        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.mypatientoldappointments, parent, false)
        return PatientOldAppointmentViewHolder(v)

    }

    override fun getItemCount(): Int {
        return oldAppointmentsList.size
    }

    override fun onBindViewHolder(holder: PatientOldAppointmentViewHolder, position: Int) {
        val oldAppointment = oldAppointmentsList[position]
        holder.date.text = oldAppointment.appointmentDate()
        holder.time.text = oldAppointment.timeSlot()
    }

    class PatientOldAppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var date: TextView
        var time: TextView

        init {
            date = itemView.findViewById(R.id.appointmentdate)
            time = itemView.findViewById(R.id.appointmenttime)
        }
    }
}

