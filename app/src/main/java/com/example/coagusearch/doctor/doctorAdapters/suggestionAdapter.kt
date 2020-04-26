package com.example.coagusearch.doctor.doctorAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R

class suggestionAdapter(val companies: MutableList<String>) :
    RecyclerView.Adapter<suggestionAdapter.NotificationCardViewHolder>() {
    private val TYPE_EMER = 1
    private val TYPE_MED = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationCardViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.suggestioncard, parent, false)
        } else {
            v = LayoutInflater.from(parent.context).inflate(R.layout.suggestioncard2, parent, false)
        }

        return NotificationCardViewHolder(v)

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

    override fun onBindViewHolder(holder: NotificationCardViewHolder, position: Int) {
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
            TYPE_EMER
        } else {
            TYPE_MED
        }

    }

    class NotificationCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

