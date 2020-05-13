package com.example.coagusearch.patient


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.patient.PatientAdapters.OldAppointmentsAdapterAF
import com.example.coagusearch.R
import com.example.coagusearch.network.Appointment.model.AppointmentRepository
import com.example.coagusearch.network.Appointment.request.DeleteAppointmentsForUserRequest
import com.example.coagusearch.network.Appointment.response.UserAppointmentResponse
import com.example.coagusearch.network.Users.response.SingleAppointmentResponse
import kotlinx.android.synthetic.main.fragment_appointmentspage.*
import kotlinx.android.synthetic.main.nextappointmentnewcancel.view.*
import org.koin.android.ext.android.get


class appointmentspage : Fragment() {
    var oldAppointmentList = ArrayList<SingleAppointmentResponse>()
    var adapter: OldAppointmentsAdapterAF? = null
    var myAppointments: UserAppointmentResponse? = null
    var appointmentList: ListView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_appointmentspage, container, false)
        val appointmentRepository: AppointmentRepository = get()
        appointmentRepository.getMyAppointments(this.context!!, this)
        appointmentList = view.findViewById(R.id.appointmentslist)
        return view
    }

    fun setAdapter(userAppointmentResponse: UserAppointmentResponse) {
        myAppointments = userAppointmentResponse
        setNextAppointment()
        oldAppointmentList = ArrayList(myAppointments!!.oldAppointment)
        adapter = OldAppointmentsAdapterAF(context!!, oldAppointmentList)
        appointmentList!!.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appointmentNextAppointment.visibility = View.GONE
        appointmentmenurequest.setOnClickListener {
            val intent = Intent(getActivity(), NewAppointment::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            );
        }
    }

    private fun setNextAppointment() {
        if (myAppointments!!.nextAppointment != null) {
            appointmentNextAppointment.visibility = View.VISIBLE
            appointmentNextAppointment.appointmentnextappointmentdoctor.text =
                myAppointments!!.nextAppointment!!.DoctorName()
            appointmentNextAppointment.appointmentnextappointmentdate.text =
                myAppointments!!.nextAppointment!!.appointmentDate()
            appointmentNextAppointment.appointmentnextappointmenttime.text =
                myAppointments!!.nextAppointment!!.timeSlot()
            appointmentNextAppointment.appointmentcancel.setOnClickListener {
                val builder = AlertDialog.Builder(
                    context!!,
                    R.style.AlertDialogStyle
                )
                builder.setMessage(R.string.deletesure)
                builder.setPositiveButton(R.string.yes) { dialog, which ->
                    val appointmentRepository: AppointmentRepository = get()
                    appointmentRepository.deleteAppointmentForUser(
                        DeleteAppointmentsForUserRequest(myAppointments!!.nextAppointment!!.id),
                        this.context!!
                    )
                    (context as main).onRestart()
                }
                builder.setNegativeButton(R.string.no) { dialog, which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            appointmentmenurequest.visibility = View.GONE
        } else {
            appointmentNextAppointment.visibility = View.GONE
        }
    }

}
