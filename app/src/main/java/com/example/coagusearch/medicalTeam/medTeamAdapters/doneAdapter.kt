package com.example.coagusearch.medicalTeam.medTeamAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.MedicalPrepareFragment
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderIDRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse

class doneAdapter (val suggestions: MutableList<DoctorBloodOrderResponse>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_MED = 1
    private val TYPE_BLOOD = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamdonecardmedicine, parent, false)
            return DoneMedicineViewHolder(v)
        } else {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamdonecardblood, parent, false)
            return DoneBloodViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        if(suggestion.kind.equals("Medicine")){
            (holder as DoneMedicineViewHolder).patientName.text=suggestion.patientName+" "+suggestion.patientSurname
            holder.dosage.text=suggestion.quantity.toString()+" "+suggestion.unit
            holder.medNmae.text=suggestion.productType
            holder.additionalText.text=suggestion.additionalNote
        }
        else{
            (holder as DoneBloodViewHolder).patientName.text=suggestion.patientName+" "+suggestion.patientSurname
            holder.quantity.text=suggestion.quantity.toString()+" "+suggestion.unit
            holder.medNmae.text=suggestion.productType
            holder.additionalText.text=suggestion.additionalNote
            holder.bloodType.text=suggestion.getBloodTypeAsString()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (suggestions.get(position).kind.equals("Medicine")) {
            TYPE_MED
        } else {
            TYPE_BLOOD
        }

    }

    class DoneMedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var dosage = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)


    }
    class DoneBloodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var quantity = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var bloodType = itemView.findViewById<TextView>(R.id.textView36)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
    }
}