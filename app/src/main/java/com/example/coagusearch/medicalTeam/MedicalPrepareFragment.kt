package com.example.coagusearch.medicalTeam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.medTeamAdapters.doneAdapter
import com.example.coagusearch.medicalTeam.medTeamAdapters.waitingAdapter
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.response.MedicalBloodOrderResponse
import kotlinx.android.synthetic.main.fragment_medical_prepare.*
import kotlinx.android.synthetic.main.medicalsegmented.*
import org.koin.android.ext.android.get

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MedicalPrepareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalPrepareFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_prepare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doneList.visibility = View.GONE
        getData()
        button1.setOnClickListener {
            waitinglist.visibility = View.VISIBLE
            doneList.visibility = View.GONE
            button1.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button1.setTextColor(resources.getColor(R.color.white))
            button2.setBackgroundResource(R.drawable.segmentedbuttonback)
            button2.setTextColor(resources.getColor(R.color.colorPrimary))

        }
        button2.setOnClickListener {
            waitinglist.visibility = View.GONE
            doneList.visibility = View.VISIBLE
            button2.setBackgroundResource(R.drawable.segmentedbuttonchecked)
            button2.setTextColor(resources.getColor(R.color.white))
            button1.setBackgroundResource(R.drawable.segmentedbuttonback)
            button1.setTextColor(resources.getColor(R.color.colorPrimary))
        }


    }
    fun getData(){
        val bloodOrderRepository: BloodOrderRepository = get()
        bloodOrderRepository.getOrdersForMedical(this.context!!,this)
    }

    fun setData(orders:MedicalBloodOrderResponse){
        doneList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        doneList.adapter = doneAdapter(orders.doneOrderList.toMutableList(),this.context!!)
        val bloodOrderRepository: BloodOrderRepository = get()
        waitinglist.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        waitinglist.adapter = waitingAdapter(orders.todoOrderList.toMutableList(),bloodOrderRepository,this.context!!,this)
    }


}
