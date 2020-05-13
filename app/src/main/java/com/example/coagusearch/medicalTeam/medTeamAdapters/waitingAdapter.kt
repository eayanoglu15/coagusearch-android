package com.example.coagusearch.medicalTeam.medTeamAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.MedicalPrepareFragment
import org.koin.android.ext.android.get
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderIDRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse
import org.koin.android.ext.android.get

class waitingAdapter(val suggestions: MutableList<DoctorBloodOrderResponse>, bloodRep:BloodOrderRepository, c: Context, f: MedicalPrepareFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var bloodBank=bloodRep
    var context=c
    var MedicalPrepareFragment=f
    private val TYPE_MED = 1
    private val TYPE_BLOOD = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamwaitingcardmedicine, parent, false)
            return WaitingMedicineViewHolder(v)
        } else {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamwaitingcardblood, parent, false)
            return WaitingBloodViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        if(suggestion.kind.equals("Medicine")){
            (holder as WaitingMedicineViewHolder).patientName.text=suggestion.patientName+" "+suggestion.patientSurname
            holder.dosage.text=suggestion.quantity.toString()+" "+suggestion.unit
            holder.medNmae.text=suggestion.productType
            holder.additionalText.text=suggestion.additionalNote
            holder.button.setOnClickListener {
                bloodBank.setOrderReady(BloodOrderIDRequest(suggestion.bloodOrderId),context,MedicalPrepareFragment)
            }

        }
        else{
            (holder as WaitingBloodViewHolder).patientName.text=suggestion.patientName+" "+suggestion.patientSurname
            holder.quantity.text=suggestion.quantity.toString()+" "+suggestion.unit
            holder.medNmae.text=suggestion.productType
            holder.additionalText.text=suggestion.additionalNote
            holder.bloodType.text=suggestion.getBloodTypeAsString()
            holder.button.setOnClickListener {
                bloodBank.setOrderReady(BloodOrderIDRequest(suggestion.bloodOrderId),context,MedicalPrepareFragment)

            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (suggestions.get(position).kind.equals("Medicine")) {
            TYPE_MED
        } else {
            TYPE_BLOOD
        }

    }


    class WaitingMedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var dosage = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
        var button =itemView.findViewById<Button>(R.id.button4)


    }
    class WaitingBloodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var quantity = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var bloodType = itemView.findViewById<TextView>(R.id.textView36)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
        var button =itemView.findViewById<Button>(R.id.button4)
    }
}