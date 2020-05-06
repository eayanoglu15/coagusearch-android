package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.PatientData.response.SuggestionResponse
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse


class statusAdapter(val suggestions: MutableList<DoctorBloodOrderResponse>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_MED = 1
    private val TYPE_BLOOD = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.suggestionsmall, parent, false)
            return SuggestionCard2ViewHolder(v)
        } else {
            v = LayoutInflater.from(parent.context).inflate(R.layout.patientbloodorderconfirmed, parent, false)
            return PatientBloodOrderViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        if(suggestion.kind.equals("Medicine")){
            (holder as SuggestionCard2ViewHolder).dosage.text=suggestion.quantity.toString()+" "+suggestion.unit
            holder.medname.text=suggestion.productType

        }
        else{
            (holder as PatientBloodOrderViewHolder).unit.text=suggestion.quantity.toString()+" "+suggestion.unit
            holder.name.text=suggestion.productType
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (suggestions.get(position).kind.equals("Medicine")) {
            TYPE_MED
        } else {
            TYPE_BLOOD
        }

    }

    class SuggestionCard1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name = itemView.findViewById<TextView>(R.id.textView17)
        var unit = itemView.findViewById<TextView>(R.id.textView29)
        var medname = itemView.findViewById<TextView>(R.id.textView30)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
        var suggetionNumber = itemView.findViewById<TextView>(R.id.textView32)
        var suggetionRation = itemView.findViewById<TextView>(R.id.textView33)


    }
    class SuggestionCard2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var dosage = itemView.findViewById<TextView>(R.id.textView29)
        var medname = itemView.findViewById<TextView>(R.id.textView30)


    }
    class PatientBloodOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name = itemView.findViewById<TextView>(R.id.name)
        var unit = itemView.findViewById<TextView>(R.id.unit)
    }
}
