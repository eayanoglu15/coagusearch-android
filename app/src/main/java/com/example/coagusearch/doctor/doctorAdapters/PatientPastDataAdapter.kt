package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.doctor.PastMicroTemData
import com.example.coagusearch.doctor.doctorMyPatient


class PatientPastDataAdapter(var companies: MutableList<String>, var context: Context) :
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

    fun add(item: String, position: Int) {
        companies.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item: String) {
        val position = companies.indexOf(item)
        companies.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: PastDataViewHolder, position: Int) {
        val company = companies[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(this.context, PastMicroTemData::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (companies.get(position).equals("a")) {
            TYPE_RED
        } else if (companies.get(position).equals("b")) {
            TYPE_BLUE
        } else {
            TYPE_RED
        }
    }

    class PastDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}

