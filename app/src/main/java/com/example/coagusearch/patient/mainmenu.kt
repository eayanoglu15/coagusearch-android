package com.example.coagusearch.patient


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.coagusearch.R
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.response.PatientMainScreenResponse
import kotlinx.android.synthetic.main.fragment_mainmenu.*
import kotlinx.android.synthetic.main.nextappointmentnew.view.*
import org.koin.android.ext.android.get


/**
 * A simple [Fragment] subclass.
 */
class mainmenu : Fragment() {

    var myticketlist:ListView?=null
    var patientMainScreenResponse:PatientMainScreenResponse?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_mainmenu,container,false)
        val userRepository: UsersRepository = get()
        userRepository.getPatientMainScreen(this.context!!,this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextAppointment.visibility=View.GONE
        missingInfo.visibility=View.GONE
    }

    fun setView(response: PatientMainScreenResponse){
        patientMainScreenResponse=response
        println(response.toString())
        if(!patientMainScreenResponse!!.patientMissingInfo){
            missingInfo.visibility=View.GONE
        }
        else{
            missingInfo.visibility=View.VISIBLE
            missingInfo.setOnClickListener{
                val intent =  Intent(getActivity(),
                    accountPage::class.java)
                startActivity(intent)
                getActivity()?.overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                );
            }
        }
        if(patientMainScreenResponse!!.patientNextAppointment!=null){
            nextAppointment.visibility=View.VISIBLE
            nextAppointment.doctorName.setText(patientMainScreenResponse!!.patientNextAppointment?.DoctorName())
            nextAppointment.dateOfNext.setText(patientMainScreenResponse!!.patientNextAppointment?.appointmentDate())
            nextAppointment.timeSlotNext.setText(patientMainScreenResponse!!.patientNextAppointment?.timeSlot())
        }
        else{
            nextAppointment.visibility=View.GONE
        }
    }

    fun setViewError(){
        missingInfo.visibility=View.GONE
        nextAppointment.visibility=View.GONE
    }
}
