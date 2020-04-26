package com.example.coagusearch.medicalTeam.medTeamAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R


class MedTeamMainPageAdapter(val companies: MutableList<String>) :
    RecyclerView.Adapter<MedTeamMainPageAdapter.PatientCard>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientCard {

        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.setpatientreadycard, parent, false)
        return PatientCard(v)

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

    override fun onBindViewHolder(holder: PatientCard, position: Int) {
        val company = companies[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context, "${position} is clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.itemView.setOnLongClickListener {
            remove(company)
            return@setOnLongClickListener true
        }
    }

    class PatientCard(itemView: View) : RecyclerView.ViewHolder(itemView)
}

