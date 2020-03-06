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
import com.example.coagusearch.Class.MedicineFrequency
import kotlinx.android.synthetic.main.fragment_appointmentspage.*
import kotlinx.android.synthetic.main.fragment_personpage.*
import kotlinx.android.synthetic.main.med.view.*

/**
 * A simple [Fragment] subclass.
 */
class personpage : Fragment() {
    var listicket=ArrayList<Medicine>()
    var adapter: personpage.TicketAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_personpage,container,false)
        listicket.add(Medicine("Apranax",MedicineFrequency.TwiceADay,1))
        listicket.add(Medicine("Arveles",MedicineFrequency.AfterBreakfast,2))
        listicket.add(Medicine("Parol",MedicineFrequency.BeforeBreakfast,1))
        listicket.add(Medicine("xxxx",MedicineFrequency.OnceADay,1))
        var myticketlist: ListView
        adapter= personpage.TicketAdapter(this.context!!, listicket)
        myticketlist=view.findViewById<ListView>(R.id.medlist)
        myticketlist.adapter=adapter

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editbutton.setOnClickListener {
            val intent =  Intent(getActivity(),AddMedicine::class.java)
            startActivity(intent)
        }
    }



    class TicketAdapter: BaseAdapter {
        var listOfTicket=ArrayList<Medicine>()
        var context: Context?=null
        constructor(context: Context, listOfTicket:ArrayList<Medicine>):super(){
            this.listOfTicket=listOfTicket
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val ticket=listOfTicket[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.med, null)
                myView.medName.text=ticket.name
                myView.medFreq.text = when(ticket.frequency) {
                    MedicineFrequency.OnceADay -> "Once a day"
                    MedicineFrequency.BeforeBreakfast -> "Before Breakfast"
                    MedicineFrequency.TwiceADay -> "Twice a day"
                    MedicineFrequency.AfterBreakfast -> "After Breakfast"
                    MedicineFrequency.BeforeLunch -> "Before lunch"
                    MedicineFrequency.AfterLunch -> "After lunch"
                    else -> "No Info"
                }
                myView.medDos.text=ticket.dosage.toString()
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
