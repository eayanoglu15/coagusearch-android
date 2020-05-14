package com.example.coagusearch.doctor.doctorAdapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.notifications.response.NotificationResponse


class DoctorNotificationsAdapter(var companies: MutableList<NotificationResponse>, var context:Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_E= 1
    private val TYPE_A=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (viewType == 1) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.informationcard, parent, false)
            return ECardViewHolder(v)
        }
        else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.patientmainscreennotificationcard, parent, false)
            return NotificationCardViewHolder(v)
        }

    }

    override fun getItemCount(): Int {
        if (companies.size==0)
        return 1
        else
        return companies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(companies.size==0){
            (holder as ECardViewHolder).info.text= context.getString(R.string.DoctorNotificationsInfo)
        }
        else{
            val company = companies[position]
            (holder as NotificationCardViewHolder).notificationMessage.text=company.notificationString
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (companies.size==0) {
            TYPE_E
        } else {
            TYPE_A
        }
    }


    class NotificationCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationMessage = itemView.findViewById<TextView>(R.id.notificationText)
    }

    class ECardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var info = itemView.findViewById<TextView>(R.id.infoText)
    }
}


