package com.example.coagusearch.patient.PatientAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coagusearch.R
import com.example.coagusearch.network.notifications.response.NotificationResponse

class PatientMainScreenNotificationsAdapter(var notificatons: MutableList<NotificationResponse>) :
    RecyclerView.Adapter<PatientMainScreenNotificationsAdapter.NotificationCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationCardViewHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.patientmainscreennotificationcard, parent, false)
        return NotificationCardViewHolder(v)
    }
    override fun getItemCount(): Int {
        return notificatons.size
    }

    override fun onBindViewHolder(holder: NotificationCardViewHolder, position: Int) {
        val notification = notificatons[position]
        holder.notificationMessage.text = notification.notificationString
    }

    class NotificationCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationMessage = itemView.findViewById<TextView>(R.id.notificationText)
    }
}

