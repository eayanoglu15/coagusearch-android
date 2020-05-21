package com.example.coagusearch.medicalTeam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.DoctorNotificationsAdapter
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.AmbulancePatientRequest
import com.example.coagusearch.network.notifications.model.NotificationRepository
import com.example.coagusearch.network.notifications.response.NotificationResponse
import kotlinx.android.synthetic.main.fragment_medical_home.*
import kotlinx.android.synthetic.main.reportpatientcard.*
import org.koin.android.ext.android.get


class medicalHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifyDoctorText.setOnClickListener {
            if(editText2.text.toString().length>0&&!editText2.text.toString().equals("null")){
                val userRepository: UsersRepository = get()
                userRepository.saveAmbulancePatient(AmbulancePatientRequest(editText2.text.toString()),this.context!!,this)
                //editText2.text.clear()
                //editText2.clearFocus()
            }
            else{
                Toast.makeText(this.context,getString(R.string.enterTCplease),Toast.LENGTH_LONG).show()
            }
        }
        getData()
    }
    private fun getData(){
        val notificationRepository: NotificationRepository = get()
        notificationRepository.getPageMedical(this.context!!,this)

    }

    fun setData(response:List<NotificationResponse>){
        medicalnotificationsRecyclerview.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        medicalnotificationsRecyclerview.adapter = DoctorNotificationsAdapter(response.toMutableList(),this.context!!)
    }

    override fun onResume() {
        super.onResume()

    }


    fun AddPatient(){
            Toast.makeText(this.context,getString(R.string.pleaseaddpatient),Toast.LENGTH_LONG).show()
            val intent = Intent(this.context, MedTeamAddPatient::class.java)
            intent.putExtra("patientID",editText2.text.toString().toLong())
            startActivity(intent)
    }
}
