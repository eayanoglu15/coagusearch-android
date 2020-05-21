package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.SingleAppointmentResponse


class PatientOldAppointmentAdapter(val oldAppointmentsList: MutableList<SingleAppointmentResponse>,var context:Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_E=1
    private val TYPE_N=2
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if(viewType==1){
          var   v= LayoutInflater.from(parent.context)
                .inflate(R.layout.informationcard, parent, false)
            return ECardViewHolder(v)
        }
        else {
            var v = LayoutInflater.from(parent.context)
                .inflate(R.layout.mypatientoldappointments, parent, false)
            return PatientOldAppointmentViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        if(oldAppointmentsList.size==0)
            return 1
        else
            return oldAppointmentsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (oldAppointmentsList.size==0) {
            TYPE_E
        } else {
            TYPE_N
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(oldAppointmentsList.size==0){
            (holder as ECardViewHolder).notificationText.text=context.getString(R.string.DoctorPatientPastAppointmentInfo)
        }
        else {
            val oldAppointment = oldAppointmentsList[position]
            (holder as PatientOldAppointmentViewHolder).date.text = oldAppointment.appointmentDate()
            holder.time.text = oldAppointment.timeSlot()
         }
        }

    class PatientOldAppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView
        var time: TextView

        init {
            date = itemView.findViewById(R.id.appointmentdate)
            time = itemView.findViewById(R.id.appointmenttime)
        }
    }

    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }
}

