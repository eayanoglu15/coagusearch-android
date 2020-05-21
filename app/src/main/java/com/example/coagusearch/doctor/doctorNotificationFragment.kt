package com.example.coagusearch.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.DoctorNotificationsAdapter
import com.example.coagusearch.network.notifications.model.NotificationRepository
import com.example.coagusearch.network.notifications.response.NotificationResponse
import kotlinx.android.synthetic.main.fragment_doctor_notification.*
import org.koin.android.ext.android.get

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [doctorNotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class doctorNotificationFragment : Fragment() {
    val list:List<NotificationResponse> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_doctor_notification, container, false)
    }
    private fun getData(){
        val notificationRepository: NotificationRepository = get()
        notificationRepository.getPage(this.context!!,this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        notificationRecyclerView.layoutManager =
            LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        notificationRecyclerView.adapter = DoctorNotificationsAdapter(list.toMutableList(),this.context!!)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    fun setData(response:List<NotificationResponse>){
        (notificationRecyclerView.adapter as DoctorNotificationsAdapter).companies = response.toMutableList()
        (notificationRecyclerView.adapter as DoctorNotificationsAdapter).notifyDataSetChanged()
    }


}
