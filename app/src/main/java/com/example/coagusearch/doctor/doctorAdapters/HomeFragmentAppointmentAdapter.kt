package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.TodayPatientDetail


class HomeFragmentAppointmentAdapter(var todaysAppointmentsList : MutableList<TodayPatientDetail>) :
    RecyclerView.Adapter<HomeFragmentAppointmentAdapter.AppointmentCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentCardViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.homepageappointmentcard,parent,false)
        return AppointmentCardViewHolder(v)
    }
    override fun getItemCount(): Int {
        return todaysAppointmentsList.size
    }
    fun add(item:TodayPatientDetail, position:Int) {
        todaysAppointmentsList.add(position, item)
        notifyItemInserted(position)
    }
    fun remove(item:TodayPatientDetail) {
        val position = todaysAppointmentsList.indexOf(item)
        todaysAppointmentsList.removeAt(position)
        notifyItemRemoved(position)
    }
    override fun onBindViewHolder(holder: AppointmentCardViewHolder, position: Int) {
        val appointment = todaysAppointmentsList[position]
        holder.patientName.text=appointment.userName+" "+appointment.userSurname
        holder.time.text=appointment.appointmentHour.getTimeAsString()
    }
    class AppointmentCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var patientName = itemView.findViewById<TextView>(R.id.patientName)
        var time = itemView.findViewById<TextView>(R.id.timeText)
    }
}

