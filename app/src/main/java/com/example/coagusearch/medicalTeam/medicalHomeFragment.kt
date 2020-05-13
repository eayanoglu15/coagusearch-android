package com.example.coagusearch.medicalTeam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.coagusearch.R
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.request.AmbulancePatientRequest
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
                editText2.text.clear()
                editText2.clearFocus()
            }
            else{
                Toast.makeText(this.context,"Lütfen Hasta Kimlik Numarasını Giriniz",Toast.LENGTH_LONG).show()
            }
        }
    }
}
