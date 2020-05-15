package com.example.coagusearch.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.PatientsFragmentPatientAdapter
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.response.TodayPatientDetail
import com.example.coagusearch.network.Users.response.UserResponse
import kotlinx.android.synthetic.main.fragment_doctor_patients.*
import org.koin.android.ext.android.get


/**
 * A simple [Fragment] subclass.
 */
class doctorPatientsFragment : Fragment() {
    var myPatientsList = mutableListOf<UserResponse>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_doctor_patients, container, false)
       getData()
        return view
    }
    private fun getData(){
        val userRepository: UsersRepository = get()
        userRepository.getMyPatients(this.context!!, this)
    }

    override fun onResume() {
        super.onResume()
        getData()
        getActivity()!!.getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        patientRecyclerView.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        patientRecyclerView.adapter = PatientsFragmentPatientAdapter(myPatientsList, this.context!!)

    }

    fun setPatientList(patientList: List<UserResponse>) {
        myPatientsList = patientList.toMutableList()
       // myPatientsList= emptyList<UserResponse>().toMutableList()
        patientRecyclerView.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        patientRecyclerView.adapter = PatientsFragmentPatientAdapter(myPatientsList, this.context!!)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (patientRecyclerView.adapter as PatientsFragmentPatientAdapter).filter.filter(
                    newText
                )
                return false
            }
        })

    }


}

