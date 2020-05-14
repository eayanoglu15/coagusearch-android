package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.PatientData.response.SuggestionResponse

class suggestionAdapter(val suggestions: MutableList<SuggestionResponse>,var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_MED = 1
    private val TYPE_BLOOD = 2
    private val TYPE_E=3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.suggestioncard2, parent, false)
            return SuggestionCard2ViewHolder(v)
        } else if(viewType == 2)  {
            v = LayoutInflater.from(parent.context).inflate(R.layout.suggestioncard, parent, false)
            return SuggestionCard1ViewHolder(v)
        }
        else{
            v= LayoutInflater.from(parent.context)
                .inflate(R.layout.informationcard, parent, false)
            return myPatientMedAdapter.ECardViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        if(suggestions.size==0)
            return 1
        else
        return suggestions.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(suggestions.size==0){
            (holder as ECardViewHolder).notificationText.text=context.getString(R.string.DoctorPatientSuggestionInfo)
        }
        else{
        val suggestion = suggestions[position]
        if(suggestion.kind.equals("Medicine")){
                (holder as SuggestionCard2ViewHolder).name.text=suggestion.diagnosis
                holder.dosage.text=suggestion.quantity.toString()+" "+suggestion.unit
                holder.medname.text=suggestion.product
            holder.additionalText.visibility=View.GONE
            }
            else {
            (holder as SuggestionCard1ViewHolder).name.text = suggestion.diagnosis
            holder.unit.text = suggestion.quantity.toString() + " " + suggestion.unit
            holder.medname.text = suggestion.product
            holder.additionalText.visibility = View.GONE
        }
        }


    }

    override fun getItemViewType(position: Int): Int {
        if(suggestions.size==0){
            return TYPE_E
        }
        else if (suggestions.get(position).kind.equals("Medicine")) {
             return TYPE_MED
        } else {
             return TYPE_BLOOD
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
        var name = itemView.findViewById<TextView>(R.id.textView17)
        var dosage = itemView.findViewById<TextView>(R.id.textView29)
        var medname = itemView.findViewById<TextView>(R.id.textView30)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
        var suggetionNumber = itemView.findViewById<TextView>(R.id.textView32)
        var suggetionRation = itemView.findViewById<TextView>(R.id.textView33)

    }
    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }

}

