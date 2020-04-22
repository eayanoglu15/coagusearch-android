package com.example.coagusearch.doctor

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coagusearch.R
import com.example.coagusearch.doctor.doctorAdapters.BloodBankOrderAdapter
import kotlinx.android.synthetic.main.bloodordercard.*
import kotlinx.android.synthetic.main.fragment_doctor_blood_bank.*


class doctorBloodBankFragment : Fragment() {
    val list= mutableListOf("a","b","c","a","c","b")
    private var m_Text = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_blood_bank, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ordersRecyclerView.layoutManager= LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL,false)
         ordersRecyclerView.adapter=BloodBankOrderAdapter(list)
        addNoteText.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context!!)
            builder.setTitle("Title")

            // Set up the input
            // Set up the input

            val input = EditText(this.context!!)
            input.isSingleLine=false
            input.setText(m_Text)
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text

            builder.setTitle("Add Your Note")
            // Set up the buttons
            // Set up the buttons
            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which -> m_Text = input.text.toString() })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.setView(input)
            builder.show()
        }
        makeorderbutton.setOnClickListener {
            m_Text=""
            editText.text=null
            editText.clearFocus()
            Toast.makeText(this.context!!,"Your order sended",Toast.LENGTH_LONG).show()

        }
    }
}
