package com.example.coagusearch.medicalTeam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.medicalTeam.medTeamAdapters.MedTeamPatientsAdapter
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.response.UserResponse
import kotlinx.android.synthetic.main.fragment_medical_team_patients.*
import org.koin.android.ext.android.get
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [medicalTeamPatientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class medicalTeamPatientsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_team_patients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        addPatientButton.setOnClickListener {
            val intent = Intent(this.context, MedTeamAddPatient::class.java)
            startActivity(intent)
        }


    }

    private fun getData(){
        val userRepository: UsersRepository = get()
        userRepository.getMyPatientsMed(this.context!!, this)
    }

    fun setPatientList(list:List<UserResponse>){
        patientRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        patientRecyclerView.adapter = MedTeamPatientsAdapter(list.toMutableList(), this.context!!)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                (patientRecyclerView.adapter as MedTeamPatientsAdapter).filter.filter(newText)
                return false
            }
        })

        textView27.text=getString(R.string.totalpatients)+" "+list.size.toString()

    }


    override fun onResume() {
        super.onResume()
        getData()
    }


}
