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
import android.widget.Toast
import com.example.coagusearch.Class.Medicine
import com.example.coagusearch.network.Users.model.UsersRepository
import kotlinx.android.synthetic.main.fragment_appointmentspage.*
import kotlinx.android.synthetic.main.mainmenuticket.view.*
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 */
class appointmentspage : Fragment() {
    var listOfTicket=ArrayList<MenuTicket>()
    var adapter: appointmentspage.MenuTicketAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userRepository: UsersRepository = get()
        userRepository.getUserInfo()
        val view: View = inflater.inflate(R.layout.fragment_appointmentspage,container,false)
        listOfTicket.add(MenuTicket("new",R.drawable.datasended))
        listOfTicket.add(MenuTicket("appointment",R.drawable.datasended))
        listOfTicket.add(MenuTicket("appointment",R.drawable.analysis))
        listOfTicket.add(MenuTicket("appointment",R.drawable.analysis))
        listOfTicket.add(MenuTicket("appointment",R.drawable.analysis))
        listOfTicket.add(MenuTicket("appointment",R.drawable.analysis))
        listOfTicket.add(MenuTicket("appointment",R.drawable.analysis))
        var myticketlist: ListView
        adapter= appointmentspage.MenuTicketAdapter(context!!, listOfTicket)
        myticketlist=view.findViewById(R.id.appointmentslist)
        myticketlist.adapter=adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appointmentmenurequest.setOnClickListener {
            val intent =  Intent(getActivity(),NewAppointment::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }

    class MenuTicketAdapter: BaseAdapter {
        var listOfTicket=ArrayList<MenuTicket>()
        var context: Context?=null
        constructor(context: Context, listOfTicket: ArrayList<MenuTicket>):super(){
            this.listOfTicket=listOfTicket
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val ticket=listOfTicket[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            if (ticket.text.equals("appointment")){
                var myView = inflater.inflate(R.layout.myappointmentsall, null)
                return myView
            }
            else if(ticket.text.equals("new")){
                var myView = inflater.inflate(R.layout.appointmentmenunext, null)
                return myView
            }
            else {
                var myView = inflater.inflate(R.layout.mainmenuticket, null)
                myView.tickettext.text = ticket.text
                myView.ticketimage.setImageResource(ticket.image!!)
                return myView
            }
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
