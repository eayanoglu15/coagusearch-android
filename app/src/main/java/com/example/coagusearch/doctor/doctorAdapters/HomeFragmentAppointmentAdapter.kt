package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.TodayPatientDetail


class HomeFragmentAppointmentAdapter(var todaysAppointmentsList: MutableList<TodayPatientDetail>,var context:Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_E=1
    private val TYPE_N=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      var v:View?=null
       if(viewType==TYPE_E){
        v= LayoutInflater.from(parent.context)
            .inflate(R.layout.informationcard, parent, false)
           return ECardViewHolder(v)
       }
        else{
        v= LayoutInflater.from(parent.context)
            .inflate(R.layout.homepageappointmentcard, parent, false)
           return AppointmentCardViewHolder(v)
         }

    }


    override fun getItemCount(): Int {
        if(todaysAppointmentsList.size==0)
            return 1
        else
            return todaysAppointmentsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (todaysAppointmentsList.size==0) {
            TYPE_E
        } else {
            TYPE_N
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)==TYPE_E){
            (holder as ECardViewHolder).notificationText.text=context.getString(R.string.DoctorHomeInfo)
        }
        else {
            val appointment = todaysAppointmentsList[position]
            (holder as AppointmentCardViewHolder).patientName.text = appointment.userName + " " + appointment.userSurname
            holder.time.text = appointment.appointmentHour.getTimeAsString()
        }
    }

    class AppointmentCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var patientName = itemView.findViewById<TextView>(R.id.patientName)
        var time = itemView.findViewById<TextView>(R.id.timeText)
    }

    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }

}

