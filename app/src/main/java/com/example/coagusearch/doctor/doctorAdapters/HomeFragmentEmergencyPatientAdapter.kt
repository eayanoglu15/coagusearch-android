package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        holder.patientName.text =
            emergencyPatientDetail.userName.toString().capitalize() + " " + emergencyPatientDetail.userSurname.toString().capitalize()
        holder.time.text = emergencyPatientDetail.arrivalHour.getTimeAsString()
        if (!emergencyPatientDetail.isUserAtAmbulance){
            holder.ambulance.visibility = View.INVISIBLE
            println(emergencyPatientDetail.isUserAtAmbulance.toString()+"Print ambulance")
        }
        else{
            println(emergencyPatientDetail.isUserAtAmbulance.toString()+"Print ambulance")
            holder.ambulance.visibility = View.VISIBLE
        }
        if (!emergencyPatientDetail.isDataReady){
            println(emergencyPatientDetail.isDataReady.toString()+"Print ambulance")
            holder.check.visibility = View.INVISIBLE
        }
        else{
            println(emergencyPatientDetail.isDataReady.toString()+"Print ambulance")
            holder.check.visibility = View.VISIBLE
        }
    }

    class HomePagePatientCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var patientName = itemView.findViewById<TextView>(R.id.patientName)
        var time = itemView.findViewById<TextView>(R.id.timeText)
        var check = itemView.findViewById<ImageView>(R.id.checkImage)
        var ambulance = itemView.findViewById<ImageView>(R.id.ambulanceImage)
    }
}