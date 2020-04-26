package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R


class BloodBankOrderAdapter(val companies: MutableList<String>) :
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

    fun add(item: String, position: Int) {
        companies.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item: String) {
        val position = companies.indexOf(item)
        companies.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: BloodBankOrderViewHolder, position: Int) {
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

    override fun getItemViewType(position: Int): Int {
        return if (companies.get(position).equals("a")) {
            TYPE_CONFIRMED
        } else if (companies.get(position).equals("b")) {
            TYPE_DENIED
        } else {
            TYPE_PENDING
        }
    }

    class BloodBankOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
