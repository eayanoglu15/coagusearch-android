package com.example.coagusearch.medicalTeam.medTeamAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.MedicalPrepareFragment
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.request.BloodOrderIDRequest
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse

class waitingAdapter(val suggestions: MutableList<DoctorBloodOrderResponse>, bloodRep:BloodOrderRepository, c: Context, f: MedicalPrepareFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var bloodBank=bloodRep
    var context=c
    var MedicalPrepareFragment=f
    private val TYPE_MED = 1
    private val TYPE_BLOOD = 2
    private val TYPE_E=3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if(viewType==TYPE_E){
            v= LayoutInflater.from(parent.context)
                .inflate(R.layout.informationcard, parent, false)
            return ECardViewHolder(v)
        }
        else if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamwaitingcardmedicine, parent, false)
            return WaitingMedicineViewHolder(v)
        } else {
            v = LayoutInflater.from(parent.context).inflate(R.layout.medteamwaitingcardblood, parent, false)
            return WaitingBloodViewHolder(v)
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
            (holder as ECardViewHolder).notificationText.text=context.getString(R.string.MedicalTODO)
        }
        else {
            val suggestion = suggestions[position]
            if (suggestion.kind.equals("Medicine")) {
                if (suggestion.patientName == null || suggestion.patientSurname == null) {
                    (holder as WaitingMedicineViewHolder).patientName.visibility = View.GONE
                    holder.patientNameImage.visibility = View.GONE
                } else {
                    (holder as WaitingMedicineViewHolder).patientName.text =
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
                holder.button.setOnClickListener {
                    bloodBank.setOrderReady(
                        BloodOrderIDRequest(suggestion.bloodOrderId),
                        context,
                        MedicalPrepareFragment
                    )
                }

            } else {
                if (suggestion.patientName == null || suggestion.patientSurname == null) {
                    (holder as WaitingBloodViewHolder).patientName.visibility = View.GONE
                    holder.patientNameImage.visibility = View.GONE
                } else {
                    (holder as WaitingBloodViewHolder).patientName.text =
                        suggestion.patientName + " " + suggestion.patientSurname
                }
                holder.quantity.text = suggestion.quantity.toString() + " " + suggestion.unit
                if(suggestion.productType.equals("FFP")){
                    holder.medNmae.text= "Fresh Frozen Plasma"
                }
                else {
                    holder.medNmae.text = suggestion.productType
                }
                if (suggestion.additionalNote != null) {
                    if(!suggestion.additionalNote!!.toString().equals("")) {
                        holder.additionalText.text = suggestion.additionalNote
                    }
                    else {
                        holder.additionalText.visibility = View.GONE
                    }
                } else {
                    holder.additionalText.visibility = View.GONE
                }
                holder.bloodType.text = suggestion.getBloodTypeAsString()
                holder.button.setOnClickListener {
                    bloodBank.setOrderReady(
                        BloodOrderIDRequest(suggestion.bloodOrderId),
                        context,
                        MedicalPrepareFragment
                    )

                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(suggestions.size==0){
            TYPE_E
        }
        else if (suggestions.get(position).kind.equals("Medicine")) {
            TYPE_MED
        } else {
            TYPE_BLOOD
        }

    }


    class WaitingMedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientNameImage=itemView.findViewById<ImageView>(R.id.imageView5)
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var dosage = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
        var button =itemView.findViewById<Button>(R.id.button4)


    }
    class WaitingBloodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var patientNameImage=itemView.findViewById<ImageView>(R.id.imageView5)
        var patientName = itemView.findViewById<TextView>(R.id.textView35)
        var quantity = itemView.findViewById<TextView>(R.id.textView29)
        var medNmae = itemView.findViewById<TextView>(R.id.textView30)
        var bloodType = itemView.findViewById<TextView>(R.id.textView36)
        var additionalText = itemView.findViewById<TextView>(R.id.textView31)
        var button =itemView.findViewById<Button>(R.id.button4)
    }
    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }
}