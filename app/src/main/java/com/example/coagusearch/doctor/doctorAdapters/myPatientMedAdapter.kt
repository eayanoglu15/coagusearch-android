package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.RegularMedication.request.MedicineInfoType
import com.example.coagusearch.network.RegularMedication.response.MedicineInfoResponse
import com.example.coagusearch.network.Users.response.TodayPatientDetail


class myPatientMedAdapter(var medicineList: MutableList<MedicineInfoResponse>) :
    RecyclerView.Adapter<myPatientMedAdapter.medCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): medCardViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.doctormedicinecard, parent, false)
        return medCardViewHolder(v)
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: medCardViewHolder, position: Int) {
        val med = medicineList[position]
        if(med.mode==MedicineInfoType.KEY){
            holder.medName.text=med.key
        }else{
            holder.medName.text=med.custom
        }
        holder.frequency.text=med.frequency.toString()
        holder.dosage.text=med.dosage.toString()+" Doz"

    }

    class medCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var medName: TextView
        var frequency: TextView
        var dosage: TextView

        init {
            medName = itemView.findViewById(R.id.medName)
            frequency = itemView.findViewById(R.id.medFreq)
            dosage = itemView.findViewById(R.id.medDos)
        }
    }
}
