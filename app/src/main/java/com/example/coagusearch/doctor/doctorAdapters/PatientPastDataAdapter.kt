package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.doctor.PastMicroTemData
import com.example.coagusearch.doctor.doctorMyPatient
import com.example.coagusearch.network.PatientData.response.UserBloodTestHistoryResponse


class PatientPastDataAdapter(var companies: MutableList<UserBloodTestHistoryResponse>, var context: Context) :
    RecyclerView.Adapter<PatientPastDataAdapter.PastDataViewHolder>() {
    private val TYPE_RED = 1
    private val TYPE_BLUE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastDataViewHolder {
        var v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.pastdatacardred, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.pastdatacardblue, parent, false)
        }
        return PastDataViewHolder(v)
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    override fun onBindViewHolder(holder: PastDataViewHolder, position: Int) {
        val company = companies[position]
        holder.dataText.text=company.testDate.getAsString()
        holder.itemView.setOnClickListener {
            val intent = Intent(this.context, PastMicroTemData::class.java)
            intent.putExtra("testId",company.id)
            context.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        /*
        return if (companies.get(position).equals("a")) {
            TYPE_RED
        } else if (companies.get(position).equals("b")) {
            TYPE_BLUE
        } else {
            TYPE_RED
        }
         */
        return TYPE_RED
    }

    class PastDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dataText = itemView.findViewById<TextView>(R.id.textView24)
    }
}

