package com.example.coagusearch.medicalTeam.medTeamAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.MedTeamEditPatient
import com.example.coagusearch.network.Users.response.UserResponse
import java.util.*


class MedTeamPatientsAdapter(val patientsList: MutableList<UserResponse>, var context: Context) :
    RecyclerView.Adapter<MedTeamPatientsAdapter.PatientViewHolder>(),
    Filterable {
    var FilteredList: MutableList<UserResponse> = mutableListOf()

    init {
        FilteredList = patientsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mypatientcard, parent, false)
        return PatientViewHolder(v)
    }

    override fun getItemCount(): Int {
        if(patientsList.size==0)
            return 1
        else
            return FilteredList.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        if(patientsList.size==0){
            holder.itemDetail.text=context.getString(R.string.DoctorPatientInfo)
        }
        else {
            val patient = FilteredList[position]
            holder.itemDetail.text = FilteredList[position].getFullName().capitalize()
            holder.itemView.setOnClickListener {
                val intent = Intent(context,MedTeamEditPatient ::class.java)
                intent.putExtra("patient", patient)
                context.startActivity(intent)

            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch: String = constraint.toString()
                if (charSearch.isEmpty()) {
                    FilteredList = patientsList
                } else {
                    val resultList: MutableList<UserResponse> = mutableListOf()
                    for (row in patientsList) {
                        if (row.getFullName().toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    FilteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = FilteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                FilteredList = results?.values as MutableList<UserResponse>
                notifyDataSetChanged()
            }

        }
    }

    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemDetail: TextView
        init {
            itemDetail = itemView.findViewById(R.id.patientnameMyPatient)
        }
    }

    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationText = itemView.findViewById<TextView>(R.id.infoText)
    }
}
