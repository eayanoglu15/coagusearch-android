package com.example.coagusearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_medicine.*
import kotlinx.android.synthetic.main.activity_new_appointment.*

class NewAppointment : AppCompatActivity() {
    private var doctorPickerIsON:Boolean=false
    private var datePickerIsON:Boolean=false
    private var slotPickerIsON:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)
        /*
        timeSlotPicker.visibility=View.GONE
        appointmentDatePicker.visibility=View.GONE
        doctorPicker.visibility=View.GONE

        doctorPicker.setOnClickListener {
            if(doctorPickerIsON) {
                doctorPicker.visibility = View.GONE
                doctorPickerIsON=false
                ocYourDoctor.setImageResource(R.drawable.downarrow)

            }

        }
        ocYourDoctor.setOnClickListener{
            if(doctorPickerIsON) {
                doctorPicker.visibility = View.GONE
                doctorPickerIsON=false
                ocYourDoctor.setImageResource(R.drawable.downarrow)
            }
            else{
                doctorPicker.visibility = View.VISIBLE
                doctorPickerIsON=true
                ocYourDoctor.setImageResource(R.drawable.uparrow)
            }
        }

         */

    }
}
