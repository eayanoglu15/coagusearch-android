package com.example.coagusearch.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.HomeFragmentAppointmentAdapter
import com.example.coagusearch.doctor.doctorAdapters.HomeFragmentEmergencyPatientAdapter
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.Users.response.DoctorMainScreenResponse
import com.example.coagusearch.network.Users.response.EmergencyPatientDetail
import com.example.coagusearch.network.Users.response.TodayPatientDetail
import kotlinx.android.synthetic.main.fragment_doctor_home.*
import org.koin.android.ext.android.get


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [doctorHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class doctorHomeFragment : Fragment() {
    var emergencyPatientList = mutableListOf<EmergencyPatientDetail>()
    var appointmentList = mutableListOf<TodayPatientDetail>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_doctor_home, container, false)
        getData()
        return view
    }
    private fun getData(){
        val userRepository: UsersRepository = get()
        userRepository.getDoctorMainScreen(this.context!!, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emergencyPatientRecyclerView.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.HORIZONTAL, false)
        emergencyPatientRecyclerView.adapter =
            HomeFragmentEmergencyPatientAdapter(emergencyPatientList)
        //emergencyPatientRecyclerView.itemAnimator=animator
        appointmentsRecyclerView.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        appointmentsRecyclerView.adapter = HomeFragmentAppointmentAdapter(appointmentList,this.context!!)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(emergencyPatientRecyclerView)
    }

    fun attachToViews(mainScreenResponse: DoctorMainScreenResponse) {
        emergencyPatientList = mainScreenResponse.emergencyPatients.toMutableList()
        appointmentList = mainScreenResponse.todayAppointments.toMutableList()
        (emergencyPatientRecyclerView.adapter as HomeFragmentEmergencyPatientAdapter).emergencyPatientList =
            emergencyPatientList
        (emergencyPatientRecyclerView.adapter as HomeFragmentEmergencyPatientAdapter).notifyDataSetChanged()
        (appointmentsRecyclerView.adapter as HomeFragmentAppointmentAdapter).todaysAppointmentsList =
            appointmentList
        (appointmentsRecyclerView.adapter as HomeFragmentAppointmentAdapter).notifyDataSetChanged()
        emergencyPatientNumber.text = getString(R.string.TotalPatients) + emergencyPatientList.size.toString()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

}
