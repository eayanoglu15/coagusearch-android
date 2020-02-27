package com.example.coagusearch


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_mainmenu.*
import kotlinx.android.synthetic.main.mainmenuticket.view.*


/**
 * A simple [Fragment] subclass.
 */
class mainmenu : Fragment() {

    var listOfTicket=ArrayList<MenuTicket>()
    var adapter:MenuTicketAdapter?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_mainmenu,container,false)
        listOfTicket.add(MenuTicket("new",R.drawable.datasended))
        listOfTicket.add(MenuTicket("appointment",R.drawable.datasended))
        listOfTicket.add(MenuTicket("Your data sended",R.drawable.datasended))
        listOfTicket.add(MenuTicket("The analysis completed",R.drawable.analysis))
        listOfTicket.add(MenuTicket("Your doctor has informed",R.drawable.doctorinformed))
        listOfTicket.add(MenuTicket("Suggestion of the doctor",R.drawable.doctorsuggestion))
        var myticketlist:ListView
        adapter= MenuTicketAdapter(context!!,listOfTicket)
        myticketlist=view.findViewById<ListView>(R.id.myticketlist)
        myticketlist.adapter=adapter
        return view
    }

    class MenuTicketAdapter:BaseAdapter{
        var listOfTicket=ArrayList<MenuTicket>()
        var context:Context?=null
        constructor(context:Context,listOfTicket:ArrayList<MenuTicket>):super(){
            this.listOfTicket=listOfTicket
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val ticket=listOfTicket[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            if (ticket.text.equals("appointment")){
                var myView = inflater.inflate(R.layout.menunextappointment, null)
                return myView
            }
             else if(ticket.text.equals("new")){
                var myView = inflater.inflate(R.layout.menunewappointmnet, null)
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
