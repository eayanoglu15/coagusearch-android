package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.DoctorBloodOrderResponse


class BloodBankOrderAdapter(val companies: MutableList<DoctorBloodOrderResponse>) :
    RecyclerView.Adapter<BloodBankOrderAdapter.BloodBankOrderViewHolder>() {
    private val TYPE_CONFIRMED = 1
    private val TYPE_DENIED = 2
    private val TYPE_PENDING = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodBankOrderViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.bloodorderconfirmed, parent, false)
        } else if (viewType == 2) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.bloodorderdenied, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.bloodorderpending, parent, false)
        }

        return BloodBankOrderViewHolder(v)

    }
    override fun getItemCount(): Int {
        return companies.size
    }

    override fun onBindViewHolder(holder: BloodBankOrderViewHolder, position: Int) {
        val company = companies[position]
        holder.unit.text=company.quantity.toString()+" Units"
        if(company.productType.equals("FFP")){
            holder.name.text="Fresh Frozen Plasma "
        }
        else{
            holder.name.text=company.productType
        }

        holder.type.text=company.getBloodTypeAsString()
    }

    override fun getItemViewType(position: Int): Int {
        /*
        return if (companies.get(position).equals("a")) {
            TYPE_CONFIRMED
        } else if (companies.get(position).equals("b")) {
            TYPE_DENIED
        } else {
            TYPE_PENDING
        }
         */
        return TYPE_CONFIRMED
    }

    class BloodBankOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var unit = itemView.findViewById<TextView>(R.id.unit)
        var name = itemView.findViewById<TextView>(R.id.name)
        var type = itemView.findViewById<TextView>(R.id.type)
    }
}
