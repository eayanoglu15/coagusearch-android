package com.example.coagusearch.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.BloodBankOrderAdapter
import kotlinx.android.synthetic.main.fragment_doctor_blood_bank.*
import kotlinx.android.synthetic.main.fragment_doctor_patients.*


class doctorBloodBankFragment : Fragment() {
    val list= mutableListOf("a","b","c","a","c","b")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_blood_bank, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersRecyclerView.layoutManager= LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL,false)
         ordersRecyclerView.adapter=BloodBankOrderAdapter(list)
    }
}
