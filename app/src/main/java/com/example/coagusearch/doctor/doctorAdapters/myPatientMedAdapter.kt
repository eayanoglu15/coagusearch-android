package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.response.TodayPatientDetail


class myPatientMedAdapter(var medicineList : MutableList<String>) :
    RecyclerView.Adapter<myPatientMedAdapter.medCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): medCardViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.doctormedicinecard,parent,false)
        return medCardViewHolder(v)
    }
    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: medCardViewHolder, position: Int) {
        val med = medicineList[position]
    }
    class medCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}
