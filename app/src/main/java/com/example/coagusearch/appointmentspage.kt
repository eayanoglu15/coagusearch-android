package com.example.coagusearch


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.network.Appointment.model.AppointmentRepository
import com.example.coagusearch.network.Appointment.request.DeleteAppointmentsForUserRequest
import com.example.coagusearch.network.Appointment.response.UserAppointmentResponse
import com.example.coagusearch.network.Users.response.SingleAppointmentResponse
import kotlinx.android.synthetic.main.fragment_appointmentspage.*
import kotlinx.android.synthetic.main.nextappointmentnewcancel.view.*
import kotlinx.android.synthetic.main.oldappointmentcard.view.*
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 */


class appointmentspage : Fragment() {
    var listOfTicket=ArrayList<SingleAppointmentResponse>()
    var adapter: appointmentspage.MenuTicketAdapter?=null
    var myAppointments:UserAppointmentResponse?=null
    var myticketlist: ListView?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_appointmentspage,container,false)
        val appointmentRepository: AppointmentRepository = get()
        appointmentRepository.getMyAppointments(this.context!!,this)
        myticketlist=view.findViewById(R.id.appointmentslist)
        return view
    }

    fun setAdapter(userAppointmentResponse: UserAppointmentResponse){
        myAppointments=userAppointmentResponse
        if(myAppointments!!.nextAppointment!=null){
            appointmentNextAppointment.visibility=View.VISIBLE
            appointmentNextAppointment.appointmentnextappointmentdoctor.text=myAppointments!!.nextAppointment!!.DoctorName()
            appointmentNextAppointment.appointmentnextappointmentdate.text=myAppointments!!.nextAppointment!!.appointmentDate()
            appointmentNextAppointment.appointmentnextappointmenttime.text=myAppointments!!.nextAppointment!!.timeSlot()
            appointmentNextAppointment.appointmentcancel.setOnClickListener {
                val builder = AlertDialog.Builder(context!!, R.style.AlertDialogStyle)
                builder.setTitle("Next Appointment")
                builder.setMessage("Silmek istediğinize emin misiniz=")
                builder.setPositiveButton("Evet"){dialog, which ->
                    val appointmentRepository: AppointmentRepository = get()
                    appointmentRepository.deleteAppointmentForUser(
                        DeleteAppointmentsForUserRequest(myAppointments!!.nextAppointment!!.id),this.context!!)

                }
                builder.setNegativeButton("Hayır"){
                        dialog, which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

            }
            appointmentmenurequest.visibility=View.GONE
        }
        else{
            appointmentNextAppointment.visibility=View.GONE
        }
        var iter=myAppointments!!.oldAppointment.iterator()
        while(iter.hasNext()){
            listOfTicket.add(iter.next())
        }
        adapter= appointmentspage.MenuTicketAdapter(context!!, listOfTicket)
        myticketlist!!.adapter=adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appointmentNextAppointment.visibility=View.GONE
        appointmentmenurequest.setOnClickListener {
            val intent =  Intent(getActivity(),NewAppointment::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }

    class MenuTicketAdapter: BaseAdapter {
        var listOfTicket=ArrayList<SingleAppointmentResponse>()
        var context: Context?=null
        constructor(context: Context, listOfTicket: ArrayList<SingleAppointmentResponse>):super(){
            this.listOfTicket=listOfTicket
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val ticket=listOfTicket[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.oldappointmentcard, null)
                myView.appointentdoctor.text=ticket.DoctorName()
                myView.appointmentdate.text=ticket.appointmentDate()
                myView.appointmenttime.text=ticket.timeSlot()
                return myView
        }
        override fun getItem(p0: Int): Any {
            return listOfTicket[p0]
        }
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfTicket.size
        }


    }


}
