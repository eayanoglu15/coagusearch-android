package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.RegularMedication.request.MedicineInfoType
import com.example.coagusearch.network.RegularMedication.response.MedicineInfoResponse



class myPatientMedAdapter(var medicineList: MutableList<MedicineInfoResponse>,var context:Context) :
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
        else {
           v =
               LayoutInflater.from(parent.context)
                   .inflate(R.layout.doctormedicinecard, parent, false)
           return medCardViewHolder(v)
       }
       }

    override fun getItemCount(): Int {
        if(medicineList.size==0)
            return 1
        else

         return medicineList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(medicineList.size==0){
           (holder as ECardViewHolder).notificationText.text=context.getString(R.string.DoctorPatientMedInfo)
       }
        else {
           val med = medicineList[position]
           if (med.mode == MedicineInfoType.KEY) {
               (holder as medCardViewHolder).medName.text = med.key.toString().capitalize()
           } else {
               (holder as medCardViewHolder).medName.text = med.custom!!.toString().capitalize()
           }
           holder.frequency.text = med.frequency!!.title.toString().capitalize()
           holder.dosage.text = med.dosage.toString() + " Doz"
       }
    }


    override fun getItemViewType(position: Int): Int {
        return if (medicineList.size==0) {
            TYPE_E
        } else {
            TYPE_N
        }
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

    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }

}
