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
import com.example.coagusearch.network.RegularMedication.request.MedicineInfoType
import com.example.coagusearch.network.RegularMedication.response.MedicineInfoResponse
import com.example.coagusearch.network.Users.response.UserResponse
import kotlinx.android.synthetic.main.fragment_personpage.*
import kotlinx.android.synthetic.main.med.view.*
/**
 * A simple [Fragment] subclass.
 */
class personpage : Fragment() {
    var medicines=ArrayList<MedicineInfoResponse>()
    var adapter: personpage.TicketAdapter?=null
    var userResponse:UserResponse?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_personpage,container,false)
        userResponse=UserInfoSingleton.instance.userInfo
        if(UserInfoSingleton.instance.medInfo!!.userDrugs!=null){
            medicines=ArrayList(UserInfoSingleton.instance.medInfo!!.userDrugs)
        }
        var myticketlist: ListView
        adapter= personpage.TicketAdapter(this.context!!, medicines)
        myticketlist=view.findViewById<ListView>(R.id.medlist)
        myticketlist.adapter=adapter
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editbutton.setOnClickListener {
            val intent =  Intent(getActivity(),accountPage::class.java)
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        profileNewMed.setOnClickListener {
            val intent =  Intent(getActivity(),AddMedicine::class.java)
            intent.putExtra("type","new")
            startActivity(intent)
            getActivity()?.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        profileFragment_name.text=userResponse!!.name+" "+userResponse!!.surname
        val birthdate=getString(R.string.birthdate)
        profileFragment_Age.text=birthdate+": "+userResponse!!.dateOfBirth
        val height=getString(R.string.height)
        val weight=getString(R.string.weight)
        profileFragment_height.text=height+":"+userResponse!!.height.toString()
        profileFragment_weight.text=weight+":"+userResponse!!.weight.toString()
    }

    class TicketAdapter: BaseAdapter {
        var listOfTicket=ArrayList<MedicineInfoResponse>()
        var context: Context?=null
        constructor(context: Context, listOfTicket:ArrayList<MedicineInfoResponse>):super(){
            this.listOfTicket=listOfTicket
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val ticket=listOfTicket[p0]
            var inflater=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflater.inflate(R.layout.med, null)
            val intent =  Intent(context,AddMedicine::class.java)
            intent.putExtra("type","edit")
            if(ticket.mode==MedicineInfoType.KEY) {
                myView.medName.text = ticket.key!!.toUpperCase()
                intent.putExtra("Name",ticket.key)
            }
            else{
                myView.medName.text = ticket.custom
                intent.putExtra("Name",ticket.custom)
            }
                myView.medFreq.text=ticket.frequency!!.title
                myView.medDos.text=ticket.dosage.toString()
                myView.editButtonMed.setOnClickListener{
                    intent.putExtra("id",ticket.id)
                    intent.putExtra("freq",ticket.frequency.title)
                    intent.putExtra("dos",ticket.dosage.toString())
                    context!!.startActivity(intent)

                }
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
