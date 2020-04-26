package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.EmergencyPatientDetail

class HomeFragmentEmergencyPatientAdapter(var emergencyPatientList: MutableList<EmergencyPatientDetail>) :
    RecyclerView.Adapter<HomeFragmentEmergencyPatientAdapter.HomePagePatientCardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePagePatientCardViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.homepagepatientcard, parent, false)
        return HomePagePatientCardViewHolder(v)
    }

    override fun getItemCount(): Int {
        return emergencyPatientList.size
    }

    fun add(item: EmergencyPatientDetail, position: Int) {
        emergencyPatientList.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item: EmergencyPatientDetail) {
        val position = emergencyPatientList.indexOf(item)
        emergencyPatientList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: HomePagePatientCardViewHolder, position: Int) {
        val emergencyPatientDetail = emergencyPatientList[position]
        /*
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context,"${position} is clicked",
            Toast.LENGTH_SHORT).show() }
        holder.itemView.setOnLongClickListener {
            remove(company)
            return@setOnLongClickListener true
        }
         */

        holder.patientName.text =
            emergencyPatientDetail.userName.toString().capitalize() + " " + emergencyPatientDetail.userSurname.toString().capitalize()
        holder.time.text = emergencyPatientDetail.arrivalHour.getTimeAsString()
        if (!emergencyPatientDetail.isUserAtAmbulance && !emergencyPatientDetail.isDataReady) {
            holder.check.visibility = View.GONE
            holder.ambulance.visibility = View.GONE
        } else if (!emergencyPatientDetail.isUserAtAmbulance && emergencyPatientDetail.isDataReady) {
            holder.ambulance.visibility = View.INVISIBLE
        } else if (emergencyPatientDetail.isUserAtAmbulance && !emergencyPatientDetail.isDataReady) {
            holder.check.setImageResource(R.drawable.ambulancecopy)
            holder.ambulance.visibility = View.INVISIBLE
        } else {

        }


    }

    class HomePagePatientCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var patientName = itemView.findViewById<TextView>(R.id.patientName)
        var time = itemView.findViewById<TextView>(R.id.timeText)
        var check = itemView.findViewById<ImageView>(R.id.checkImage)
        var ambulance = itemView.findViewById<ImageView>(R.id.ambulanceImage)
    }
}