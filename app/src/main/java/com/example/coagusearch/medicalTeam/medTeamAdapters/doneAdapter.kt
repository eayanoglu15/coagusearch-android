package com.example.coagusearch.medicalTeam.medTeamAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse

class doneAdapter (val suggestions: MutableList<DoctorBloodOrderResponse>,val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_MED = 1
    private val TYPE_BLOOD = 2
    private  val TYPE_E=3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if(viewType==TYPE_E){
            v= LayoutInflater.from(parent.context)
                .inflate(R.layout.informationcard, parent, false)
            return ECardViewHolder(v)
        }
        else if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamdonecardmedicine, parent, false)
            return DoneMedicineViewHolder(v)
        } else {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamdonecardblood, parent, false)
            return DoneBloodViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        if(suggestions.size==0)
            return 1
        else
            return suggestions.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (suggestions.size == 0) {
            (holder as ECardViewHolder).notificationText.text=context.getString(R.string.MedicalDONE)
        } else {
            val suggestion = suggestions[position]
            if (suggestion.kind.equals("Medicine")) {
                if (suggestion.patientName == null || suggestion.patientSurname == null) {
                    (holder as DoneMedicineViewHolder).patientName.visibility = View.GONE
                    holder.patientNameImage.visibility = View.GONE
                } else {
                    (holder as DoneMedicineViewHolder).patientName.text =
                        suggestion.patientName + " " + suggestion.patientSurname
                }
                holder.dosage.text = suggestion.quantity.toString() + " " + suggestion.unit
                if(suggestion.productType.equals("FFP")){
                    holder.medNmae.text= "Fresh Frozen Plasma"
                }
                else {
                    holder.medNmae.text = suggestion.productType
                }
                if (suggestion.additionalNote != null && !suggestion.additionalNote.toString().equals("")) {
                    holder.additionalText.text = suggestion.additionalNote
                } else {
                    holder.additionalText.visibility = View.GONE
                }
            }

        else{

            if (suggestion.patientName == null || suggestion.patientSurname == null) {
                (holder as DoneBloodViewHolder).patientName.visibility = View.GONE
                holder.patientNameImage.visibility = View.GONE
            } else {
                (holder as DoneBloodViewHolder).patientName.text =
                    suggestion.patientName + " " + suggestion.patientSurname
            }
            holder.quantity.text = suggestion.quantity.toString() + " " + suggestion.unit
                if(suggestion.productType.equals("FFP")){
                    holder.medNmae.text= "Fresh Frozen Plasma"
                }
                else {
                    holder.medNmae.text = suggestion.productType
                }
            if (suggestion.additionalNote != null && !suggestion.additionalNote.toString().equals("")) {
                holder.additionalText.text = suggestion.additionalNote.toString()
            } else {
                holder.additionalText.visibility = View.GONE
            }
            holder.bloodType.text = suggestion.getBloodTypeAsString()
        }
    }
    }

    override fun getItemViewType(position: Int): Int {
        if(suggestions.size==0)
        {
            return TYPE_E
        }
        else if (suggestions.get(position).kind.equals("Medicine")) {
           return TYPE_MED
        } else {
             return TYPE_BLOOD
        }
    }

    class DoneMedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientNameImage=itemView.findViewById<ImageView>(R.id.imageView5)
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var dosage = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
    }
    class DoneBloodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientNameImage=itemView.findViewById<ImageView>(R.id.imageView5)
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var quantity = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var bloodType = itemView.findViewById<TextView>(R.id.textView36)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
    }
    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }
}